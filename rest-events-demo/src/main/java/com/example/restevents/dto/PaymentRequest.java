package com.example.restevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentRequest {
    private String orderId;
    private BigDecimal amount;
}
