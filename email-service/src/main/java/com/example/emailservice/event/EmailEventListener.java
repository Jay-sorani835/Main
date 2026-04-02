package com.example.emailservice.event;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

@Component
public class EmailEventListener {

    @Value("${email-service.smtp.host:smtp.gmail.com}")
    private String smtpHost;

    @Value("${email-service.smtp.port:587}")
    private int smtpPort;

    @Value("${email-service.smtp.username:jaysorani835@gmail.com}")
    private String smtpUsername;

    @Value("${email-service.smtp.password:dvvzkzhdtnmvtbjy}")
    private String smtpPassword;

    private final String SENT_EMAILS_DIR = "emails/sent/";

    public EmailEventListener() {
        // Ensure directory exists
        try {
            Files.createDirectories(Paths.get(SENT_EMAILS_DIR));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    @EventListener
    public void handleSendEmailEvent(SendEmailEvent event) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(smtpHost);
            mailSender.setPort(smtpPort);
            mailSender.setUsername(smtpUsername);
            mailSender.setPassword(smtpPassword);

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "false");

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(smtpUsername);
            
            if (event.getTo() != null && !event.getTo().isBlank()) {
                helper.setTo(event.getTo().split(","));
            }
            if (event.getCc() != null && !event.getCc().isBlank()) {
                helper.setCc(event.getCc().split(","));
            }
            if (event.getBcc() != null && !event.getBcc().isBlank()) {
                helper.setBcc(event.getBcc().split(","));
            }
            
            helper.setSubject(event.getSubject());
            helper.setText(event.getBody(), true);

            if (event.getAttachments() != null) {
                for (AttachmentDto att : event.getAttachments()) {
                    helper.addAttachment(att.getFileName(), new ByteArrayResource(att.getData()));
                }
            }

            if (smtpPassword != null && !smtpPassword.isEmpty()) {
                mailSender.send(mimeMessage);
            } else {
                System.out.println("Skipping real SMTP send; Password not configured.");
            }

            // Save dummy representation to file
            String messageId = UUID.randomUUID().toString();
            Path filePath = Paths.get(SENT_EMAILS_DIR + messageId + ".txt");
            
            StringBuilder sb = new StringBuilder();
            sb.append("Message-ID: ").append(messageId).append("\n");
            sb.append("To: ").append(event.getTo()).append("\n");
            sb.append("Date: ").append(LocalDateTime.now()).append("\n");
            sb.append("Subject: ").append(event.getSubject()).append("\n\n");
            sb.append(event.getBody()).append("\n");
            
            Files.writeString(filePath, sb.toString());

            if (event.getAttachments() != null) {
                for (AttachmentDto att : event.getAttachments()) {
                    File attFile = new File(SENT_EMAILS_DIR + messageId + "_" + att.getFileName());
                    try (FileOutputStream fos = new FileOutputStream(attFile)) {
                        fos.write(att.getData());
                    }
                }
            }

            System.out.println("Email saved/sent successfully: " + messageId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
