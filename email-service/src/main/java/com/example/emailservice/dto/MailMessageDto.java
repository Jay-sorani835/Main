package com.example.emailservice.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MailMessageDto {

    private String messageId;
    private String subject;
    private String body;
    private String sender;
    private String receivers;
    private String cc;
    private String bcc;
    private LocalDateTime receivedDate;
    private List<MailAttachmentDto> attachments = new ArrayList<>();

    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getReceivers() { return receivers; }
    public void setReceivers(String receivers) { this.receivers = receivers; }
    public String getCc() { return cc; }
    public void setCc(String cc) { this.cc = cc; }
    public String getBcc() { return bcc; }
    public void setBcc(String bcc) { this.bcc = bcc; }
    public LocalDateTime getReceivedDate() { return receivedDate; }
    public void setReceivedDate(LocalDateTime receivedDate) { this.receivedDate = receivedDate; }
    public List<MailAttachmentDto> getAttachments() { return attachments; }
    public void setAttachments(List<MailAttachmentDto> attachments) { this.attachments = attachments; }

    public void addAttachment(MailAttachmentDto attachment) {
        attachments.add(attachment);
    }
}
