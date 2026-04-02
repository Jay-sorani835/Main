package com.example.emailservice.event;

public class AttachmentDto {
    private String fileName;
    private String contentType;
    private byte[] data;

    public AttachmentDto(String fileName, String contentType, byte[] data) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    public String getFileName() { return fileName; }
    public String getContentType() { return contentType; }
    public byte[] getData() { return data; }
}
