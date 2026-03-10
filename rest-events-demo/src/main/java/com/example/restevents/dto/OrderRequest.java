package com.example.restevents.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderRequest {
    private String customerEmail;
    private String productCode;
    private BigDecimal amount;
    private boolean simulateFailure; // For testing purposes
    private boolean simulateEventException; // For testing unhandled async exceptions
}
