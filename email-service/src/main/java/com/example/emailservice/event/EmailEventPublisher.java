package com.example.emailservice.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EmailEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishSendEmailEvent(String senderEmail, String to, String cc, String bcc, String subject, String body, List<AttachmentDto> attachments) {
        SendEmailEvent event = new SendEmailEvent(this, senderEmail, to, cc, bcc, subject, body, attachments);
        applicationEventPublisher.publishEvent(event);
    }
}
