package com.example.restevents.exception;

import org.springframework.http.HttpStatusCode;

public class PaymentApiException extends RuntimeException {

    private final HttpStatusCode statusCode;
    private final String responseBody;

    public PaymentApiException(String message, HttpStatusCode statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
