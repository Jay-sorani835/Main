package com.example.emailservice.dto;

public class MailAttachmentDto {

    private String id; // Use file path or unique hash for downloading
    private String fileName;
    private String contentType;

    public MailAttachmentDto(String id, String fileName, String contentType) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
}
