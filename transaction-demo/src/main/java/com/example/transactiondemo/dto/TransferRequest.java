package com.example.transactiondemo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private boolean simulateError; // True if we want to test transaction rollback
}
