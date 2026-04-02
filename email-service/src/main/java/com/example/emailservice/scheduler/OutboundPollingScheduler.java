package com.example.emailservice.scheduler;

import com.example.emailservice.service.OutboundEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class OutboundPollingScheduler {

    private static final Logger logger = LoggerFactory.getLogger(OutboundPollingScheduler.class);

    private final OutboundEmailService outboundEmailService;

    @Autowired
    public OutboundPollingScheduler(OutboundEmailService outboundEmailService) {
        this.outboundEmailService = outboundEmailService;
    }

    // Runs every 2 minutes. We can map this to the same or different property if needed
//    @Scheduled(fixedDelayString = "${email-service.scheduler.interval:120000}")
    public void scheduleOutboundProcessing() {
        logger.info("Starting scheduled outbound email polling...");
        try {
            outboundEmailService.processPendingOutboundMessages();
            logger.info("Completed scheduled outbound email polling.");
        } catch (Exception e) {
            logger.error("Error during scheduled outbound email polling: ", e);
        }
    }
}
