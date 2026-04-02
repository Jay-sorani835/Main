package com.example.emailservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Document(collection = "inbound_faxes")
@CompoundIndex(def = "{'message_id': 1, 'filename': 1}", name = "msgid_filename_idx", unique = true)
public class InboundFax {

    @Id
    private String id;

    /** Full subject line — acts as fax reference */
    private String subject;

    /** Numeric or alphanumeric fax number extracted from subject */
    @Field("fax_number")
    private String faxNumber;

    @Field("sender_email")
    private String senderEmail;

    private String filename;

    @Field("local_file_path")
    private String localFilePath;

    /** IMAP Message-ID header — used for idempotency */
    @Field("message_id")
    private String messageId;

    @Field("received_at")
    private LocalDateTime receivedAt;

    @Field("downloaded_at")
    private LocalDateTime downloadedAt;

    public void initDefaults() {
        if (downloadedAt == null) downloadedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getFaxNumber() { return faxNumber; }
    public void setFaxNumber(String faxNumber) { this.faxNumber = faxNumber; }
    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getLocalFilePath() { return localFilePath; }
    public void setLocalFilePath(String localFilePath) { this.localFilePath = localFilePath; }
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
    public LocalDateTime getReceivedAt() { return receivedAt; }
    public void setReceivedAt(LocalDateTime receivedAt) { this.receivedAt = receivedAt; }
    public LocalDateTime getDownloadedAt() { return downloadedAt; }
    public void setDownloadedAt(LocalDateTime downloadedAt) { this.downloadedAt = downloadedAt; }
}
