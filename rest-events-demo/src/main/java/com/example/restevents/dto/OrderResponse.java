package com.example.restevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
    private String status;
    private String message;
    private String orderId;
}
