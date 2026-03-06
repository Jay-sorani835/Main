package com.example.yamldemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.demo")
public class AppConfig {
    private String message;
    private String version;
    private List<String> features;

    // Standard Getters and Setters (Required!)
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }
}