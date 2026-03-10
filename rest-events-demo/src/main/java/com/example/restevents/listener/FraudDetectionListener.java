package com.example.restevents.listener;

import com.example.restevents.event.PaymentFailedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FraudDetectionListener {

    /**
     * This listener is SYNCHRONOUS.
     * It runs immediately in the same thread that published the event.
     * If this method throws an exception, it WILL bubble up and crash the main
     * request.
     */
    @EventListener
    public void handlePaymentFailedEvent(PaymentFailedEvent event) {
        log.warn("[SYNC - FRAUD] Detecting unusual activity. Payment failed for: {} Amount: {}",
                event.getCustomerEmail(), event.getAttemptedAmount());

        log.warn("[SYNC - FRAUD] Reason: {}", event.getFailureReason());
        log.warn("[SYNC - FRAUD] Account flagged for manual review.");
    }
}
