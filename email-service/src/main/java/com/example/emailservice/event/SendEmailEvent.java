package com.example.emailservice.event;

import org.springframework.context.ApplicationEvent;

import java.util.List;

public class SendEmailEvent extends ApplicationEvent {

    private final String to;
    private final String cc;
    private final String bcc;
    private final String subject;
    private final String body;
    private final List<AttachmentDto> attachments;
    private final String senderEmail;

    public SendEmailEvent(Object source, String senderEmail, String to, String cc, String bcc, String subject, String body, List<AttachmentDto> attachments) {
        super(source);
        this.senderEmail = senderEmail;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
    }

    public String getSenderEmail() { return senderEmail; }
    public String getTo() { return to; }
    public String getCc() { return cc; }
    public String getBcc() { return bcc; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public List<AttachmentDto> getAttachments() { return attachments; }
}
