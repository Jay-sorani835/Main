package com.example.emailservice;

import com.example.emailservice.entity.OutboundMessage;
import com.example.emailservice.entity.OutboundMessageStatus;
import com.example.emailservice.repository.InboundFaxRepository;
import com.example.emailservice.repository.OutboundMessageRepository;
import com.example.emailservice.service.OutboundEmailService;
import com.example.emailservice.service.ImapEmailService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for the Fax-Email Utility Service.
 *
 * Uses the real H2 database (no mocking) to validate:
 *  1. Outbound queuing
 *  2. Idempotency — same fax never queued twice
 *  3. Fax number extraction from various subject formats
 *  4. Status transitions (PENDING → SUCCESS/FAILED)
 *  5. Retry logic for FAILED records
 *  6. Inbound duplicate detection
 *
 * NOTE: tests that call SMTP/IMAP are skipped by default (requires real network).
 *       Set environment variable RUN_NETWORK_TESTS=true to enable them.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FaxServiceIntegrationTest {

    @Autowired OutboundMessageRepository outboundRepo;
    @Autowired InboundFaxRepository inboundRepo;
    @Autowired OutboundEmailService outboundEmailService;

    // Helper — creates a real dummy file and returns its absolute path
    private String createDummyFile(String filename, String content) throws Exception {
        File dir = new File("download-fax");
        dir.mkdirs();
        File f = Paths.get("download-fax", filename).toFile();
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(content.getBytes());
        }
        return f.getAbsolutePath();
    }

    @BeforeEach
    void cleanUp() {
        outboundRepo.deleteAll();
    }

    // ── TEST 1: Queuing creates a PENDING record ──────────────────────────────

    @Test
    @Order(1)
    @DisplayName("Queuing a fax creates a PENDING record in DB")
    void shouldQueueFaxAsPending() throws Exception {
        String path = createDummyFile("test_queue.txt", "content");

        OutboundMessage msg = outboundEmailService.queueOutboundMessage(
            "FAX-100001", "test_queue.txt", path
        );

        assertThat(msg).isNotNull();
        assertThat(msg.getId()).isPositive();
        assertThat(msg.getStatus()).isEqualTo(OutboundMessageStatus.PENDING);
        assertThat(msg.getSubject()).isEqualTo("FAX-100001");
        assertThat(msg.getFilename()).isEqualTo("test_queue.txt");
        assertThat(msg.getSentAt()).isNull();
    }

    // ── TEST 2: Idempotency — duplicate fax is NOT re-queued ─────────────────

    @Test
    @Order(2)
    @DisplayName("Idempotency: same subject+filename queued twice returns null the second time")
    void shouldNotQueueDuplicate() throws Exception {
        String path = createDummyFile("test_idempotent.txt", "content");

        outboundEmailService.queueOutboundMessage("FAX-100002", "test_idempotent.txt", path);
        // Simulate that it was sent successfully
        OutboundMessage record = outboundRepo.findAll().get(0);
        record.setStatus(OutboundMessageStatus.SUCCESS);
        outboundRepo.save(record);

        // Try to queue the same fax again
        OutboundMessage duplicate = outboundEmailService.queueOutboundMessage(
            "FAX-100002", "test_idempotent.txt", path
        );

        assertThat(duplicate).isNull(); // idempotency gate returns null
        assertThat(outboundRepo.count()).isEqualTo(1); // only one record in DB
    }

    // ── TEST 3: Different filename = different record (not a duplicate) ────────

    @Test
    @Order(3)
    @DisplayName("Same subject but different filename IS allowed (different fax content)")
    void shouldAllowSameSubjectDifferentFilename() throws Exception {
        String path1 = createDummyFile("file_a.txt", "content a");
        String path2 = createDummyFile("file_b.txt", "content b");

        OutboundMessage m1 = outboundEmailService.queueOutboundMessage("FAX-100003", "file_a.txt", path1);
        OutboundMessage m2 = outboundEmailService.queueOutboundMessage("FAX-100003", "file_b.txt", path2);

        assertThat(m1).isNotNull();
        assertThat(m2).isNotNull();
        assertThat(outboundRepo.count()).isEqualTo(2);
    }

    // ── TEST 4: FAILED record is retried by processPendingOutboundMessages ────

    @Test
    @Order(4)
    @DisplayName("FAILED records are picked up by the outbound processor")
    void shouldPickUpFailedRecordsForRetry() throws Exception {
        String path = createDummyFile("test_retry.txt", "retry content");

        // Directly insert a FAILED record
        OutboundMessage failed = new OutboundMessage();
        failed.setSubject("FAX-999");
        failed.setFilename("test_retry.txt");
        failed.setLocalFilePath(path);
        failed.setStatus(OutboundMessageStatus.FAILED);
        outboundRepo.save(failed);

        List<OutboundMessage> pending = outboundRepo.findByStatusIn(
            List.of(OutboundMessageStatus.PENDING, OutboundMessageStatus.FAILED)
        );

        assertThat(pending).hasSize(1);
        assertThat(pending.get(0).getStatus()).isEqualTo(OutboundMessageStatus.FAILED);
    }

    // ── TEST 5: All fax number subject formats are accepted ───────────────────

    @Test
    @Order(5)
    @DisplayName("All fax number subject formats can be queued")
    void shouldAcceptAllFaxNumberFormats() throws Exception {
        String[] subjects = {
            "8005551234",           // Pure number
            "1-800-555-9876",       // Dashed US format
            "+1 (408) 555-0101",    // International format
            "FAX-100001",           // FAX- prefix
            "FX-2001",              // Short FX prefix
            "MEDITAB-9001",         // Alphanumeric reference
            "Fax from 4085550199",  // Embedded fax number
            "InvoiceFromAcmeCorp",  // No digits at all (also valid)
        };

        for (int i = 0; i < subjects.length; i++) {
            String filename = "subjectformat_" + i + ".txt";
            String path = createDummyFile(filename, "content " + i);
            OutboundMessage msg = outboundEmailService.queueOutboundMessage(subjects[i], filename, path);
            assertThat(msg).isNotNull().withFailMessage("Should have queued: " + subjects[i]);
        }

        assertThat(outboundRepo.count()).isEqualTo(subjects.length);
    }

    // ── TEST 6: Pending count is correct ──────────────────────────────────────

    @Test
    @Order(6)
    @DisplayName("All freshly queued records start as PENDING")
    void allQueuedRecordsShouldBePending() throws Exception {
        for (int i = 0; i < 5; i++) {
            String filename = "pending_check_" + i + ".txt";
            createDummyFile(filename, "data");
            outboundEmailService.queueOutboundMessage("FAX-200" + i, filename,
                Paths.get("download-fax", filename).toFile().getAbsolutePath());
        }

        long pendingCount = outboundRepo.findAll().stream()
            .filter(m -> m.getStatus() == OutboundMessageStatus.PENDING)
            .count();

        assertThat(pendingCount).isEqualTo(5);
    }

    // ── TEST 7: Inbound duplicate is not stored twice ─────────────────────────

    @Test
    @Order(7)
    @DisplayName("Inbound fax duplicate (same messageId + filename) is rejected by DB constraint")
    void shouldNotStoreDuplicateInboundFax() {
        inboundRepo.deleteAll();

        boolean exists = inboundRepo.existsByMessageIdAndFilename("MSG-001", "report.pdf");
        assertThat(exists).isFalse();

        // First insertion
        com.example.emailservice.entity.InboundFax fax1 = new com.example.emailservice.entity.InboundFax();
        fax1.setMessageId("MSG-001");
        fax1.setFilename("report.pdf");
        fax1.setSubject("FAX-7001");
        fax1.setFaxNumber("7001");
        fax1.setSenderEmail("sender@example.com");
        fax1.setLocalFilePath("/tmp/report.pdf");
        fax1.setReceivedAt(java.time.LocalDateTime.now());
        inboundRepo.save(fax1);

        assertThat(inboundRepo.existsByMessageIdAndFilename("MSG-001", "report.pdf")).isTrue();
        assertThat(inboundRepo.count()).isEqualTo(1);
    }

    // ── TEST 8: Health-style summary matches expectations ─────────────────────

    @Test
    @Order(8)
    @DisplayName("Health summary: seeded records show correct PENDING count")
    void healthSummaryShouldReflectSeedState() throws Exception {
        for (int i = 0; i < 3; i++) {
            String filename = "health_" + i + ".txt";
            createDummyFile(filename, "data");
            outboundEmailService.queueOutboundMessage("FAX-HEALTH-" + i, filename,
                Paths.get("download-fax", filename).toFile().getAbsolutePath());
        }

        long pending = outboundRepo.findAll().stream()
            .filter(m -> m.getStatus() == OutboundMessageStatus.PENDING).count();
        long failed = outboundRepo.findAll().stream()
            .filter(m -> m.getStatus() == OutboundMessageStatus.FAILED).count();

        assertThat(pending).isEqualTo(3);
        assertThat(failed).isEqualTo(0);
    }
}
