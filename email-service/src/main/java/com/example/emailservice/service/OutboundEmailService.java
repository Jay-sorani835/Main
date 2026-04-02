package com.example.emailservice.service;

import com.example.emailservice.entity.OutboundMessage;
import com.example.emailservice.entity.OutboundMessageStatus;
import com.example.emailservice.repository.OutboundMessageRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
public class OutboundEmailService {

    private static final Logger logger = LoggerFactory.getLogger(OutboundEmailService.class);

    @Value("${email-service.smtp.host}")
    private String smtpHost;

    @Value("${email-service.smtp.port}")
    private int smtpPort;

    @Value("${email-service.smtp.username}")
    private String smtpUsername;

    @Value("${email-service.smtp.password}")
    private String smtpPassword;

    // The recipient for all faxes (configured in YAML)
    @Value("${email-service.smtp.target-email:recruitment@meditab.com}")
    private String destinationEmail;

    private final OutboundMessageRepository outboundMessageRepository;

    @Autowired
    public OutboundEmailService(OutboundMessageRepository outboundMessageRepository) {
        this.outboundMessageRepository = outboundMessageRepository;
    }

    private Session buildSmtpSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });
    }

    public void processPendingOutboundMessages() {
        List<OutboundMessage> messagesToProcess = outboundMessageRepository.findByStatusIn(
                Arrays.asList(OutboundMessageStatus.PENDING, OutboundMessageStatus.FAILED)
        );

        if (messagesToProcess.isEmpty()) {
            return;
        }

        logger.info("Found {} outbound messages to process.", messagesToProcess.size());
        Session session = buildSmtpSession();

        for (OutboundMessage msgRow : messagesToProcess) {
            try {
                // Ensure Idempotency: skip if already successfully sent (though query above protects us, good for defense)
                if (outboundMessageRepository.existsBySubjectAndFilenameAndStatus(msgRow.getSubject(), msgRow.getFilename(), OutboundMessageStatus.SUCCESS)) {
                    msgRow.setStatus(OutboundMessageStatus.SUCCESS);
                    msgRow.setSentAt(LocalDateTime.now());
                    outboundMessageRepository.save(msgRow);
                    continue;
                }

                File fileToAttach = new File(msgRow.getLocalFilePath());
                if (!fileToAttach.exists()) {
                    throw new Exception("File not found at path: " + msgRow.getLocalFilePath());
                }

                Message mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(smtpUsername));
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationEmail));
                mimeMessage.setSubject(msgRow.getSubject());

                // Mime structure
                Multipart multipart = new MimeMultipart();

                // Body part
                MimeBodyPart textBodyPart = new MimeBodyPart();
                textBodyPart.setText("Please find the attached document: " + msgRow.getFilename());
                multipart.addBodyPart(textBodyPart);

                // Attachment part
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(fileToAttach);
                multipart.addBodyPart(attachmentBodyPart);

                mimeMessage.setContent(multipart);

                // Send Email
                Transport.send(mimeMessage);

                // Acknowledgement Update
                msgRow.setStatus(OutboundMessageStatus.SUCCESS);
                msgRow.setSentAt(LocalDateTime.now());
                logger.info("Successfully sent message: ID {} - Subject '{}'", msgRow.getId(), msgRow.getSubject());

            } catch (Exception e) {
                logger.error("Failed to send message ID {}: {}", msgRow.getId(), e.getMessage());
                // Mark for retry on next interval
                msgRow.setStatus(OutboundMessageStatus.FAILED);
            }
            
            outboundMessageRepository.save(msgRow);
        }
    }

    /**
     * Call this from REST API or DB seeders when a new fax/email needs to be sent
     */
    public OutboundMessage queueOutboundMessage(String subject, String filename, String localFilePath) {
        if (outboundMessageRepository.existsBySubjectAndFilenameAndStatus(subject, filename, OutboundMessageStatus.SUCCESS)) {
            logger.info("Message for subject '{}' and file '{}' was already sent successfully. Skipping.", subject, filename);
            return null;
        }

        OutboundMessage msg = new OutboundMessage();
        msg.setSubject(subject);
        msg.setFilename(filename);
        msg.setLocalFilePath(localFilePath);
        msg.setStatus(OutboundMessageStatus.PENDING);
        msg.initDefaults();

        return outboundMessageRepository.save(msg);
    }
}
