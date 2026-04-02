package com.example.emailservice.scheduler;

import com.example.emailservice.service.ImapEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableScheduling
public class InboundPollingScheduler {

    private static final Logger logger = LoggerFactory.getLogger(InboundPollingScheduler.class);

    private final ImapEmailService imapEmailService;

    @Value("${email-service.scheduler.target-email:all}")
    private String targetEmailFilter;

    @Value("${email-service.scheduler.target-subject:any}")
    private String targetSubjectFilter;

    @Autowired
    public InboundPollingScheduler(ImapEmailService imapEmailService) {
        this.imapEmailService = imapEmailService;
    }

    @Scheduled(fixedDelayString = "${email-service.scheduler.interval:120000}")
    public void pollInbox() {
        logger.info("Starting scheduled inbound email polling...");
        try {
            imapEmailService.pollAndDownloadAttachments(targetEmailFilter, targetSubjectFilter);
            logger.info("Completed scheduled inbound email polling.");
        } catch (Exception e) {
            logger.error("Error during scheduled inbound email polling: ", e);
        }
    }
}
