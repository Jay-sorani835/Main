package com.example.emailservice.scheduler;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Component
public class EmailAttachmentScheduler {

    @Value("${email-service.imap.protocol:imaps}")
    private String protocol;

    @Value("${email-service.imap.host:imap.gmail.com}")
    private String imapHost;

    @Value("${email-service.imap.port:993}")
    private int imapPort;

    @Value("${email-service.imap.username:jaysorani835@gmail.com}")
    private String imapUsername;

    @Value("${email-service.imap.password:dvvzkzhdtnmvtbjy}")
    private String imapPassword;

    private final String ATTACHMENTS_DIR = "attachments/";

    public EmailAttachmentScheduler() {
        try {
            Files.createDirectories(Paths.get(ATTACHMENTS_DIR));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRateString = "${email-service.scheduler.interval:300000}")
    public void fetchFilteredEmailsAndAttachments() {
        if (imapPassword == null || imapPassword.isEmpty()) {
            System.out.println("Scheduler skipped IMAP fetch: Password not configured in application.yml");
            return;
        }

        Properties properties = new Properties();
        properties.put(String.format("mail.%s.host", protocol), imapHost);
        properties.put(String.format("mail.%s.port", protocol), String.valueOf(imapPort));

        try {
            Session session = Session.getDefaultInstance(properties, null);
            Store store = session.getStore(protocol);
            store.connect(imapHost, imapUsername, imapPassword);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            // Very simple backwards search. Just fetch last 10 for demo.
            for (int i = messages.length - 1; i >= 0 && i > messages.length - 10; i--) {
                Message msg = messages[i];
                parseBodyAndAttachments(msg);
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseBodyAndAttachments(Part part) throws Exception {
        if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                parseBodyAndAttachments(multipart.getBodyPart(i));
            }
        } else {
            String disposition = part.getDisposition();
            if (Part.ATTACHMENT.equalsIgnoreCase(disposition) || Part.INLINE.equalsIgnoreCase(disposition)) {
                String fileName = part.getFileName();
                if (fileName != null) {
                    File attFile = new File(ATTACHMENTS_DIR + System.currentTimeMillis() + "_" + fileName);
                    try (InputStream is = part.getInputStream();
                         FileOutputStream fos = new FileOutputStream(attFile)) {
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                    System.out.println("Downloaded attachment: " + attFile.getAbsolutePath());
                }
            }
        }
    }
}
