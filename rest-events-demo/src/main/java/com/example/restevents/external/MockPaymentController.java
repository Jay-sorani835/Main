package com.example.restevents.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Simulates a third-party payment gateway (e.g., Stripe, PayPal).
 * Our RestTemplate will call this endpoint.
 */
@Slf4j
@RestController
@RequestMapping("/api/external/payment")
public class MockPaymentController {

    @PostMapping
    public ResponseEntity<Map<String, String>> processPayment(
            @RequestBody Map<String, Object> paymentPayload,
            @RequestHeader(value = "X-Fail-Simulation", required = false, defaultValue = "false") boolean failSimulation) {

        log.info("[EXTERNAL API] Received payment request. Simulating processing...");

        if (failSimulation) {
            log.warn("[EXTERNAL API] Simulating payment failure (HTTP 400)");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Insufficient funds or invalid card details"));
        }

        log.info("[EXTERNAL API] Payment processed successfully");
        return ResponseEntity.ok(Map.of("status", "SUCCESS", "transactionId", "TXN-" + System.currentTimeMillis()));
    }
}
