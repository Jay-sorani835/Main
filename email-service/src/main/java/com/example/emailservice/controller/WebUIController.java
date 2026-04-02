package com.example.emailservice.controller;

import com.example.emailservice.dto.MailMessageDto;
import com.example.emailservice.event.AttachmentDto;
import com.example.emailservice.event.EmailEventPublisher;
import com.example.emailservice.service.ImapEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebUIController {

    private final EmailEventPublisher emailEventPublisher;
    private final ImapEmailService imapEmailService;

    @Value("${email-service.smtp.username:sender@example.com}")
    private String systemSenderEmail;

    public WebUIController(EmailEventPublisher emailEventPublisher, ImapEmailService imapEmailService) {
        this.emailEventPublisher = emailEventPublisher;
        this.imapEmailService = imapEmailService;
    }

    @GetMapping("/")
    public String root() { return "redirect:/inbox"; }

    @Value("${email-service.scheduler.target-subject:any}")
    private String defaultTargetSubject;

    @GetMapping("/inbox")
    public String inbox(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "15") int size,
                        @RequestParam(required = false) String filter) {
        
        List<MailMessageDto> recentEmails;
        try {
            if (filter != null && !filter.isEmpty()) {
                recentEmails = imapEmailService.fetchRecentInboxEmails(filter, defaultTargetSubject);
            } else {
                recentEmails = imapEmailService.fetchRecentInboxEmails();
            }
        } catch (Exception e) {
            recentEmails = new ArrayList<>();
            model.addAttribute("error", "Exception connecting to IMAP: " + e.getMessage());
        }

        // Wrap the list in a Page to maintain existing frontend pagination templates
        Page<MailMessageDto> messagePage = new PageImpl<>(
                recentEmails, 
                PageRequest.of(page, size), 
                recentEmails.size()
        );

        model.addAttribute("page", messagePage);
        return "inbox";
    }

    @GetMapping("/send")
    public String showSendPage() { return "send"; }

    @PostMapping("/send")
    public String submitSendEmailForm(@RequestParam("to") String to,
                                      @RequestParam(value = "cc", required = false) String cc,
                                      @RequestParam(value = "bcc", required = false) String bcc,
                                      @RequestParam("subject") String subject,
                                      @RequestParam("body") String body,
                                      @RequestParam(value = "files", required = false) MultipartFile[] files) {
        String senderEmail = systemSenderEmail;
        List<AttachmentDto> attachments = new ArrayList<>();

        if (files != null) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        attachments.add(new AttachmentDto(file.getOriginalFilename(), file.getContentType(), file.getBytes()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        emailEventPublisher.publishSendEmailEvent(senderEmail, to, cc, bcc, subject, body, attachments);
        return "redirect:/inbox?msg=queued";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadAttachment(@PathVariable String id) {
        // Downloading live attachments on click is a TODO for now
        // Currently it just shows the un-downloaded file name list in the inbox view
        return ResponseEntity.notFound().build();
    }
}
