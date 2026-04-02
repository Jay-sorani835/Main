package com.example.emailservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Document(collection = "outbound_messages")
@CompoundIndex(def = "{'subject': 1, 'filename': 1}", name = "subject_filename_idx")
public class OutboundMessage {

    @Id
    private String id;

    private String subject;
    private String filename;

    @Field("local_file_path")
    private String localFilePath;

    private OutboundMessageStatus status;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("sent_at")
    private LocalDateTime sentAt;

    // Lifecycle — set defaults before save
    public void initDefaults() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (status == null) status = OutboundMessageStatus.PENDING;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getLocalFilePath() { return localFilePath; }
    public void setLocalFilePath(String localFilePath) { this.localFilePath = localFilePath; }
    public OutboundMessageStatus getStatus() { return status; }
    public void setStatus(OutboundMessageStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
