package com.example.restevents.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    private final String orderId;
    private final String customerEmail;
    private final BigDecimal amount;
    private final boolean triggerException;

    public OrderCreatedEvent(Object source, String orderId, String customerEmail, BigDecimal amount,
            boolean triggerException) {
        super(source);
        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.amount = amount;
        this.triggerException = triggerException;
    }
}
