package com.example.restevents.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        // 1. Add headers here (e.g., Auth tokens, Correlation IDs)
        request.getHeaders().add("Authorization", "Bearer MOCK_TOKEN_12345");
        request.getHeaders().add("X-Correlation-ID", java.util.UUID.randomUUID().toString());

        log.info("RestTemplate Request [URI: {}, Method: {}] - Adding Headers...", request.getURI(),
                request.getMethod());

        // 2. Execute the request
        ClientHttpResponse response = execution.execute(request, body);

        // 3. Log the response (Warning: reading the body here requires a
        // BufferingClientHttpRequestFactory in configuration)
        log.info("RestTemplate Response Status: {}", response.getStatusCode());

        return response;
    }
}
