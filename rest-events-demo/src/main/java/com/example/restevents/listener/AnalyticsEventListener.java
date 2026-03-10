package com.example.restevents.listener;

import com.example.restevents.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnalyticsEventListener {

    @Async
    @EventListener
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("[ASYNC - ANALYTICS] Updating analytics for new Order: {} Amount: {}", event.getOrderId(),
                event.getAmount());

        try {
            Thread.sleep(500); // Simulate database operation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("[ASYNC - ANALYTICS] Analytics updated successfully.");
    }
}
