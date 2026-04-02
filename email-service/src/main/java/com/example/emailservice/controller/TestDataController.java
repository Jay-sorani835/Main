package com.example.emailservice.controller;

import com.example.emailservice.entity.InboundFax;
import com.example.emailservice.entity.OutboundMessage;
import com.example.emailservice.entity.OutboundMessageStatus;
import com.example.emailservice.repository.InboundFaxRepository;
import com.example.emailservice.repository.OutboundMessageRepository;
import com.example.emailservice.service.ImapEmailService;
import com.example.emailservice.service.OutboundEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.*;

/**
 * Production-grade test controller.
 * Covers fax number formats, file types, idempotency, retries, and full flow validation.
 * Remove or secure behind an admin role in production.
 */
@RestController
@RequestMapping("/test")
public class TestDataController {

    private final OutboundMessageRepository repository;
    private final OutboundEmailService outboundEmailService;
    private final ImapEmailService imapEmailService;
    private final InboundFaxRepository inboundFaxRepository;

    @Value("${email-service.scheduler.target-email:recruitment@meditab.com}")
    private String targetEmail;

    @Value("${email-service.scheduler.target-subject:any}")
    private String targetSubject;

    @Autowired
    public TestDataController(OutboundMessageRepository repository,
                              OutboundEmailService outboundEmailService,
                              ImapEmailService imapEmailService,
                              InboundFaxRepository inboundFaxRepository) {
        this.repository = repository;
        this.outboundEmailService = outboundEmailService;
        this.imapEmailService = imapEmailService;
        this.inboundFaxRepository = inboundFaxRepository;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // OUTBOUND SEEDING — 10 realistic scenarios covering all fax/subject formats
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Seeds 10 real-world outbound fax records covering:
     * - Pure fax numbers as subjects
     * - Prefixed fax refs (FAX-, FX-)
     * - International format
     * - Mixed alphanumeric
     * - Multiple file types (.txt simulating PDF/TIFF/JPG)
     */
    @PostMapping("/seed-outbound")
    public ResponseEntity<Map<String, Object>> seedOutboundData() {
        Map<String, Object> result = new LinkedHashMap<>();

        // Format: subject | filename | file content
        String[][] samples = {
            // Pure fax number subjects
            {"8005551234",          "patient_referral.txt",    "Patient referral form for fax 8005551234"},
            {"1-800-555-9876",      "lab_results.txt",         "Lab results attachment for 1-800-555-9876"},
            {"+1 (408) 555-0101",   "prescription.txt",        "Prescription document — intl format fax"},
            // Standard FAX- prefix format
            {"FAX-100001",          "invoice_Q1_2026.txt",     "Invoice Q1 2026 — FAX-100001"},
            {"FAX-100002",          "contract_renewal.txt",    "Contract renewal document — FAX-100002"},
            {"FAX-100003",          "annual_report.txt",       "Annual report 2025 — FAX-100003"},
            // Short FX prefix
            {"FX-2001",             "discharge_summary.txt",   "Patient discharge summary FX-2001"},
            // Alphanumeric fax reference
            {"MEDITAB-9001",        "insurance_claim.txt",     "Insurance claim form MEDITAB-9001"},
            // Long subject with fax number embedded
            {"Fax from 4085550199", "vendor_agreement.txt",   "Vendor agreement — fax received from 4085550199"},
            // Edge: subject has no digits at all
            {"InvoiceFromAcmeCorp", "acme_invoice.txt",       "Invoice document — subject has no fax number"},
        };

        int queued = 0, skipped = 0;

        for (String[] row : samples) {
            String subject  = row[0];
            String filename = row[1];
            String content  = row[2];

            File dummyFile = Paths.get("download-fax", filename).toFile();
            try {
                dummyFile.getParentFile().mkdirs();
                if (!dummyFile.exists()) {
                    try (FileOutputStream fos = new FileOutputStream(dummyFile)) {
                        fos.write(content.getBytes());
                    }
                }
            } catch (Exception e) {
                result.put("file_error_" + filename, e.getMessage());
                continue;
            }

            if (repository.existsBySubjectAndFilenameAndStatus(subject, filename, OutboundMessageStatus.SUCCESS)) {
                result.put(subject, "SKIPPED — already SUCCESS");
                skipped++;
                continue;
            }

            OutboundMessage msg = outboundEmailService.queueOutboundMessage(
                subject, filename, dummyFile.getAbsolutePath()
            );

            if (msg != null) {
                result.put(subject, "QUEUED — ID=" + msg.getId() + " | " + filename);
                queued++;
            } else {
                result.put(subject, "ALREADY PENDING — not re-queued");
                skipped++;
            }
        }

        result.put("_summary", "queued=" + queued + ", skipped=" + skipped);
        return ResponseEntity.ok(result);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // FULL FLOW — Seed + Send in one shot (no manual steps needed)
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * One-shot endpoint: seeds data AND immediately triggers outbound send.
     * Use this to validate the full pipeline end-to-end in one request.
     */
    @PostMapping("/run-full-flow")
    public ResponseEntity<Map<String, Object>> runFullFlow() {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("step_1_seed", "Seeding outbound records...");
        ResponseEntity<Map<String, Object>> seedResult = seedOutboundData();
        result.put("seed_result", seedResult.getBody());

        result.put("step_2_send", "Triggering outbound send...");
        outboundEmailService.processPendingOutboundMessages();

        List<Map<String, Object>> statuses = getOutboundStatus().getBody();
        long success = statuses != null ? statuses.stream().filter(m -> "SUCCESS".equals(m.get("status"))).count() : 0;
        long failed  = statuses != null ? statuses.stream().filter(m -> "FAILED".equals(m.get("status"))).count() : 0;
        long pending = statuses != null ? statuses.stream().filter(m -> "PENDING".equals(m.get("status"))).count() : 0;

        result.put("step_3_result", Map.of("SUCCESS", success, "FAILED", failed, "PENDING", pending));
        result.put("step_4_idempotency_check", "Re-seeding same data — all should be SKIPPED...");
        ResponseEntity<Map<String, Object>> idempotencyResult = seedOutboundData();
        result.put("idempotency_result", idempotencyResult.getBody());

        return ResponseEntity.ok(result);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // STATUS VIEWS
    // ─────────────────────────────────────────────────────────────────────────

    @GetMapping("/outbound-status")
    public ResponseEntity<List<Map<String, Object>>> getOutboundStatus() {
        List<OutboundMessage> all = repository.findAll();
        List<Map<String, Object>> response = all.stream().map(m -> {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("id", m.getId());
            row.put("subject", m.getSubject());
            row.put("filename", m.getFilename());
            row.put("localFilePath", m.getLocalFilePath());
            row.put("status", m.getStatus());
            row.put("createdAt", m.getCreatedAt());
            row.put("sentAt", m.getSentAt());
            return row;
        }).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/inbound-status")
    public ResponseEntity<List<Map<String, Object>>> getInboundStatus() {
        List<InboundFax> all = inboundFaxRepository.findAll();
        List<Map<String, Object>> response = all.stream().map(f -> {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("id", f.getId());
            row.put("faxNumber", f.getFaxNumber());
            row.put("subject", f.getSubject());
            row.put("senderEmail", f.getSenderEmail());
            row.put("filename", f.getFilename());
            row.put("localFilePath", f.getLocalFilePath());
            row.put("receivedAt", f.getReceivedAt());
            row.put("downloadedAt", f.getDownloadedAt());
            return row;
        }).toList();
        return ResponseEntity.ok(response);
    }

    /** Summary: counts per status for quick health check */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new LinkedHashMap<>();
        List<OutboundMessage> all = repository.findAll();
        long success = all.stream().filter(m -> m.getStatus() == OutboundMessageStatus.SUCCESS).count();
        long failed  = all.stream().filter(m -> m.getStatus() == OutboundMessageStatus.FAILED).count();
        long pending = all.stream().filter(m -> m.getStatus() == OutboundMessageStatus.PENDING).count();
        long inbound = inboundFaxRepository.count();

        health.put("outbound_total", all.size());
        health.put("outbound_SUCCESS", success);
        health.put("outbound_FAILED", failed);
        health.put("outbound_PENDING", pending);
        health.put("inbound_downloaded", inbound);
        health.put("status", (failed > 0) ? "DEGRADED" : "OK");
        return ResponseEntity.ok(health);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // MANUAL TRIGGERS
    // ─────────────────────────────────────────────────────────────────────────

    @PostMapping("/trigger-outbound")
    public ResponseEntity<Map<String, String>> triggerOutbound() {
        outboundEmailService.processPendingOutboundMessages();
        return ResponseEntity.ok(Map.of("result", "Outbound processing triggered. Check /test/outbound-status."));
    }

    @PostMapping("/trigger-inbound")
    public ResponseEntity<Map<String, String>> triggerInbound() {
        imapEmailService.pollAndDownloadAttachments(targetEmail, targetSubject);
        return ResponseEntity.accepted().body(Map.of(
            "result", "Inbound poll started in background. Check /test/inbound-status in ~15-30 seconds."
        ));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // UTILITIES
    // ─────────────────────────────────────────────────────────────────────────

    /** Reset a specific record to PENDING so it is retried on next send */
    @PostMapping("/reset/{id}")
    public ResponseEntity<Map<String, String>> resetStatus(@PathVariable String id) {
        return repository.findById(id).map(msg -> {
            msg.setStatus(OutboundMessageStatus.PENDING);
            msg.setSentAt(null);
            repository.save(msg);
            return ResponseEntity.ok(Map.of("result", "Record " + id + " reset to PENDING."));
        }).orElse(ResponseEntity.notFound().build());
    }

    /** Reset ALL failed records so they retry on next cycle */
    @PostMapping("/retry-failed")
    public ResponseEntity<Map<String, Object>> retryAllFailed() {
        List<OutboundMessage> failed = repository.findByStatusIn(
            List.of(OutboundMessageStatus.FAILED)
        );
        failed.forEach(m -> {
            m.setStatus(OutboundMessageStatus.PENDING);
            m.setSentAt(null);
        });
        repository.saveAll(failed);
        return ResponseEntity.ok(Map.of("reset", failed.size(), "message", "All FAILED records reset to PENDING."));
    }

    /** Clear only outbound records */
    @DeleteMapping("/clear-outbound")
    public ResponseEntity<Map<String, String>> clearOutbound() {
        long count = repository.count();
        repository.deleteAll();
        return ResponseEntity.ok(Map.of("result", "Deleted " + count + " outbound records."));
    }

    /** Clear only inbound records */
    @DeleteMapping("/clear-inbound")
    public ResponseEntity<Map<String, String>> clearInbound() {
        long count = inboundFaxRepository.count();
        inboundFaxRepository.deleteAll();
        return ResponseEntity.ok(Map.of("result", "Deleted " + count + " inbound fax records."));
    }

    /** Clear everything */
    @DeleteMapping("/clear-all")
    public ResponseEntity<Map<String, String>> clearAll() {
        long out = repository.count();
        long in  = inboundFaxRepository.count();
        repository.deleteAll();
        inboundFaxRepository.deleteAll();
        return ResponseEntity.ok(Map.of("result", "Deleted " + out + " outbound + " + in + " inbound records."));
    }
}
