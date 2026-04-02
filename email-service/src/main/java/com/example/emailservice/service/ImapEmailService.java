package com.example.emailservice.service;

import com.example.emailservice.dto.MailAttachmentDto;
import com.example.emailservice.dto.MailMessageDto;
import com.example.emailservice.entity.InboundFax;
import com.example.emailservice.repository.InboundFaxRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.FlagTerm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class ImapEmailService {

    private static final Logger logger = LoggerFactory.getLogger(ImapEmailService.class);

    private final InboundFaxRepository inboundFaxRepository;

    @Autowired
    public ImapEmailService(InboundFaxRepository inboundFaxRepository) {
        this.inboundFaxRepository = inboundFaxRepository;
    }

    @Value("${email-service.imap.protocol:imaps}")
    private String protocol;

    @Value("${email-service.imap.host:imap.gmail.com}")
    private String host;

    @Value("${email-service.imap.port:993}")
    private int port;

    @Value("${email-service.imap.username:admin@example.com}")
    private String username;

    @Value("${email-service.imap.password:}")
    private String password;

    @Value("${email-service.scheduler.target-email:recruitment@meditab.com}")
    private String defaultTargetEmail;

    @Value("${email-service.scheduler.target-subject:}")
    private String defaultTargetSubject;

    @Value("${email-service.local-storage.inbound-dir:download-fax}")
    private String inboundDir;

    private Store connectToStore() throws Exception {
        if (password == null || password.isEmpty()) {
            throw new Exception("IMAP password is not configured in application.yml.");
        }

        Properties properties = new Properties();
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), String.valueOf(port));
        properties.put("mail.imaps.ssl.enable", "true");
        properties.put("mail.imaps.ssl.trust", "*");
        // Prevent hanging — 10s connection timeout, 15s read timeout
        properties.put("mail.imaps.connectiontimeout", "10000");
        properties.put("mail.imaps.timeout", "15000");
        properties.put("mail.imaps.writetimeout", "10000");

        Session session = Session.getDefaultInstance(properties, null);
        Store store = session.getStore(protocol);
        store.connect(host, username, password);
        return store;
    }

    public List<MailMessageDto> fetchRecentInboxEmails() throws Exception {
        return fetchRecentInboxEmails(defaultTargetEmail, defaultTargetSubject);
    }

    // For the Inbox UI (READ_ONLY, does not mark as SEEN, does not download files)
    public List<MailMessageDto> fetchRecentInboxEmails(String targetEmailFilter, String targetSubjectFilter) throws Exception {
        List<MailMessageDto> dtos = new ArrayList<>();
        Store store = null;
        Folder inbox = null;

        try {
            store = connectToStore();
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            int MSG_LIMIT = 20;

            for (int i = messages.length - 1; i >= 0 && dtos.size() < MSG_LIMIT; i--) {
                try {
                    Message msg = messages[i];
                    String senderEmail = getSenderEmail(msg);

                    if (targetEmailFilter != null && !targetEmailFilter.isBlank() && !targetEmailFilter.equals("all")) {
                        if (!senderEmail.equalsIgnoreCase(targetEmailFilter) && !senderEmail.contains(targetEmailFilter)) {
                            continue;
                        }
                    }

                    if (targetSubjectFilter != null && !targetSubjectFilter.isBlank() && !targetSubjectFilter.equals("any")) {
                        String subject = msg.getSubject() != null ? msg.getSubject().toLowerCase() : "";
                        if (!subject.contains(targetSubjectFilter.toLowerCase())) {
                            continue;
                        }
                    }

                    MailMessageDto dto = createDtoFromMessage(msg, senderEmail);
                    parseBodyAndAttachments(msg, dto, false);
                    if (dto.getBody() == null || dto.getBody().isBlank()) {
                        dto.setBody("[Content missing or unsupported format]");
                    }
                    dtos.add(dto);

                } catch (Exception individualEmailException) {
                    System.err.println("Skipped one email due to parsing error: " + individualEmailException.getMessage());
                }
            }
        } finally {
            if (inbox != null && inbox.isOpen()) inbox.close(false);
            if (store != null) store.close();
        }

        return dtos;
    }

    // Async so the API call returns immediately — IMAP work runs in background thread
    @Async
    public void pollAndDownloadAttachments(String targetEmailFilter, String targetSubjectFilter) {
        Store store = null;
        Folder inbox = null;
        File downloadFolder = new File(inboundDir);
        if (!downloadFolder.exists()) {
            downloadFolder.mkdirs();
        }

        try {
            store = connectToStore();
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            // Fetch only unseen messages
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            // Cap per poll cycle — prevents long-running first-time scans
            int limit = Math.min(messages.length, 10);
            logger.info("Found {} unseen messages, processing up to {}.", messages.length, limit);

            for (int i = 0; i < limit; i++) {
                Message msg = messages[i];
                try {
                    String senderEmail = getSenderEmail(msg);

                    if (targetEmailFilter != null && !targetEmailFilter.isBlank() && !targetEmailFilter.equals("all")) {
                        if (!senderEmail.equalsIgnoreCase(targetEmailFilter) && !senderEmail.contains(targetEmailFilter)) {
                            continue;
                        }
                    }

                    if (targetSubjectFilter != null && !targetSubjectFilter.isBlank() && !targetSubjectFilter.equals("any")) {
                        String subject = msg.getSubject() != null ? msg.getSubject().toLowerCase() : "";
                        if (!subject.contains(targetSubjectFilter.toLowerCase())) {
                            continue;
                        }
                    }

                    MailMessageDto dto = createDtoFromMessage(msg, senderEmail);
                    parseBodyAndAttachments(msg, dto, true, dto.getSubject(), senderEmail, dto.getReceivedDate(), dto.getMessageId());

                    // Mark as seen after successful download
                    msg.setFlag(Flags.Flag.SEEN, true);
                    logger.info("Processed email from {} — subject: {}", senderEmail, msg.getSubject());

                } catch (Exception e) {
                    logger.error("Error polling email: {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.error("Fatal error during inbound poll: {}", e.getMessage());
        } finally {
            try { if (inbox != null && inbox.isOpen()) inbox.close(false); } catch (Exception ignored) {}
            try { if (store != null) store.close(); } catch (Exception ignored) {}
        }
    }

    private String getSenderEmail(Message msg) throws Exception {
        Address[] froms = msg.getFrom();
        if (froms != null && froms.length > 0) {
            return ((InternetAddress) froms[0]).getAddress();
        }
        return "Unknown Sender";
    }

    private MailMessageDto createDtoFromMessage(Message msg, String senderEmail) throws Exception {
        MailMessageDto dto = new MailMessageDto();
        String[] msgIdHeader = msg.getHeader("Message-ID");
        dto.setMessageId((msgIdHeader != null && msgIdHeader.length > 0) ? msgIdHeader[0] : String.valueOf(msg.getMessageNumber()));
        dto.setSubject(msg.getSubject() != null ? msg.getSubject() : "(No Subject)");
        dto.setSender(senderEmail);
        dto.setReceivers(username);

        if (msg.getReceivedDate() != null) {
            dto.setReceivedDate(msg.getReceivedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        } else {
            dto.setReceivedDate(LocalDateTime.now());
        }
        return dto;
    }

    private void parseBodyAndAttachments(Part part, MailMessageDto dto, boolean downloadAttachments,
                                          String subject, String senderEmail,
                                          LocalDateTime receivedAt, String messageId) throws Exception {
        if (part.isMimeType("text/plain")) {
            if (dto.getBody() == null || dto.getBody().isBlank() || "[Content missing or unsupported format]".equals(dto.getBody())) {
                try { dto.setBody(part.getContent().toString()); } catch (Exception e) {}
            }
        } else if (part.isMimeType("text/html")) {
            try { dto.setBody(part.getContent().toString()); } catch (Exception e) {}
        } else if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                parseBodyAndAttachments(multipart.getBodyPart(i), dto, downloadAttachments,
                        subject, senderEmail, receivedAt, messageId);
            }
        } else {
            String disposition = part.getDisposition();
            if (Part.ATTACHMENT.equalsIgnoreCase(disposition) || Part.INLINE.equalsIgnoreCase(disposition)) {
                String fileName = part.getFileName();
                if (fileName != null) {
                    MailAttachmentDto attDto = new MailAttachmentDto(
                            dto.getMessageId() + "_" + fileName,
                            fileName,
                            part.getContentType()
                    );
                    dto.addAttachment(attDto);

                    if (downloadAttachments) {
                        downloadFile(part, messageId, fileName, subject, senderEmail, receivedAt);
                    }
                }
            }
        }
    }

    // Overload for READ_ONLY inbox view — no DB context needed
    private void parseBodyAndAttachments(Part part, MailMessageDto dto, boolean downloadAttachments) throws Exception {
        parseBodyAndAttachments(part, dto, downloadAttachments, null, null, null, null);
    }

    private void downloadFile(Part part, String messageId, String fileName,
                              String subject, String senderEmail, LocalDateTime receivedAt) throws Exception {
        // Save using the original filename only — no messageId prefix
        Path localPath = Paths.get(inboundDir, fileName);

        // Skip if file already exists on disk
        if (Files.exists(localPath)) {
            logger.info("Skipping download — file already exists: {}", localPath.toAbsolutePath());
            return;
        }

        // Skip if this specific email's attachment is already in DB (idempotency)
        if (inboundFaxRepository.existsByMessageIdAndFilename(messageId, fileName)) {
            logger.info("Skipping — already in DB: messageId={}, file={}", messageId, fileName);
            return;
        }

        // Download to disk
        try (InputStream is = part.getInputStream();
             FileOutputStream fos = new FileOutputStream(localPath.toFile())) {
            byte[] buf = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buf)) != -1) {
                fos.write(buf, 0, bytesRead);
            }
        }
        logger.info("Downloaded attachment to: {}", localPath.toAbsolutePath());

        // Persist to DB
        InboundFax record = new InboundFax();
        record.setMessageId(messageId);
        record.setSubject(subject != null ? subject : "(No Subject)");
        record.setFaxNumber(extractFaxNumber(subject));
        record.setSenderEmail(senderEmail);
        record.setFilename(fileName);
        record.setLocalFilePath(localPath.toAbsolutePath().toString());
        record.setReceivedAt(receivedAt != null ? receivedAt : LocalDateTime.now());
        inboundFaxRepository.save(record);
        logger.info("Saved InboundFax to DB — faxNumber: {}, file: {}", record.getFaxNumber(), fileName);
    }

    /**
     * Extracts a fax-like number from the subject.
     * If subject IS a number (e.g. "8005551234"), returns it directly.
     * If subject contains digits (e.g. "FAX-8005551234" or "Fax from 800 555 1234"), extracts them.
     * Falls back to the full subject if no digits found.
     */
    private String extractFaxNumber(String subject) {
        if (subject == null || subject.isBlank()) return "UNKNOWN";
        // Return as-is if the subject looks like a pure number or standard fax ref
        if (subject.matches("[0-9\\-+() ]+")) return subject.trim();
        // Extract all digit sequences and join them
        String digits = subject.replaceAll("[^0-9]", "");
        return digits.isBlank() ? subject.trim() : digits;
    }
}

