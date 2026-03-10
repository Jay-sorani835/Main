package com.example.restevents.listener;

import com.example.restevents.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationEventListener {

    @Async
    @EventListener
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("[ASYNC - NOTIFICATION] Starting email dispatch for Order: {} to {}", event.getOrderId(),
                event.getCustomerEmail());

        try {
            Thread.sleep(1000); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (event.isTriggerException()) {
            throw new RuntimeException(
                    "Simulated exception while sending email! This should be caught by AsyncUncaughtExceptionHandler.");
        }

        log.info("[ASYNC - NOTIFICATION] Email sent successfully for Order: {}", event.getOrderId());
    }
}
