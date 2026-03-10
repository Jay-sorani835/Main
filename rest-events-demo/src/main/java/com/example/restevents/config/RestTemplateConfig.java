package com.example.restevents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final LoggingInterceptor loggingInterceptor;
    private final CustomResponseErrorHandler customResponseErrorHandler;

    @Autowired
    public RestTemplateConfig(LoggingInterceptor loggingInterceptor,
            CustomResponseErrorHandler customResponseErrorHandler) {
        this.loggingInterceptor = loggingInterceptor;
        this.customResponseErrorHandler = customResponseErrorHandler;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 1. Buffering factory allows the interceptor or error handler to read the body
        // multiple times
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        // 2. Add the custom interceptor to inject headers and log
        restTemplate.getInterceptors().add(loggingInterceptor);

        // 3. Add the custom error handler to catch 400/500s cleanly
        restTemplate.setErrorHandler(customResponseErrorHandler);

        return restTemplate;
    }
}
