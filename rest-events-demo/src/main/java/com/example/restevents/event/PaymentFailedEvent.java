package com.example.restevents.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

@Getter
public class PaymentFailedEvent extends ApplicationEvent {

    private final String customerEmail;
    private final BigDecimal attemptedAmount;
    private final String failureReason;

    public PaymentFailedEvent(Object source, String customerEmail, BigDecimal attemptedAmount, String failureReason) {
        super(source);
        this.customerEmail = customerEmail;
        this.attemptedAmount = attemptedAmount;
        this.failureReason = failureReason;
    }
}
