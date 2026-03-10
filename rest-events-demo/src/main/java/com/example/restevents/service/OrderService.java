package com.example.restevents.service;

import com.example.restevents.dto.OrderRequest;
import com.example.restevents.dto.OrderResponse;
import com.example.restevents.dto.PaymentRequest;
import com.example.restevents.event.OrderCreatedEvent;
import com.example.restevents.event.PaymentFailedEvent;
import com.example.restevents.exception.PaymentApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class OrderService {

    private final RestTemplate restTemplate;
    // Spring standard way to publish events anywhere in the app Context
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderService(RestTemplate restTemplate, ApplicationEventPublisher eventPublisher) {
        this.restTemplate = restTemplate;
        this.eventPublisher = eventPublisher;
    }

    @Value("${server.port:8080}")
    private String port;

    public OrderResponse processOrder(OrderRequest request) {
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        log.info("Processing order {} for {}", orderId, request.getCustomerEmail());

        // 1. Prepare external API call
        String paymentUrl = "http://localhost:" + port + "/api/external/payment";
        PaymentRequest paymentRequest = new PaymentRequest(orderId, request.getAmount());

        HttpHeaders headers = new HttpHeaders();
        if (request.isSimulateFailure()) {
            // Tells our Mock API to fail on purpose
            headers.add("X-Fail-Simulation", "true");
        }

        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);

        try {
            // 2. Make the REST Template Call.
            // Note: If this fails with 4xx/5xx, our CustomResponseErrorHandler converts it
            // to PaymentApiException
            log.info("Calling Payment API...");
            ResponseEntity<Map> response = restTemplate.postForEntity(paymentUrl, entity, Map.class);
            log.info("Payment successful. Transaction ID: {}", response.getBody().get("transactionId"));

            // 3. Trigger Domain Event (Success) -> Handled Asynchronously
            OrderCreatedEvent orderEvent = new OrderCreatedEvent(
                    this, orderId, request.getCustomerEmail(), request.getAmount(), request.isSimulateEventException());

            log.info("Publishing OrderCreatedEvent...");
            eventPublisher.publishEvent(orderEvent);

            return new OrderResponse("APPROVED", "Order completed successfully", orderId);

        } catch (PaymentApiException ex) {
            // 4. Trigger Domain Event (Failure) -> Handled Synchronously
            log.error("Payment API rejected the request! Status code: {} Response: {}", ex.getStatusCode(),
                    ex.getResponseBody());

            PaymentFailedEvent failEvent = new PaymentFailedEvent(
                    this, request.getCustomerEmail(), request.getAmount(), ex.getResponseBody());

            log.info("Publishing PaymentFailedEvent...");
            eventPublisher.publishEvent(failEvent);

            return new OrderResponse("DECLINED", "Payment failed: " + ex.getResponseBody(), orderId);
        }
    }
}
