package com.example.yamldemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yamldemo.config.AppConfig;

import java.util.HashMap;
import java.util.Map;
@RestController
public class DemoController {

    private final AppConfig appConfig;

    // Constructor injection is better practice!
    public DemoController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/info")
    public Map<String, Object> getAppInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("message", appConfig.getMessage());
        info.put("version", appConfig.getVersion());
        info.put("features", appConfig.getFeatures());
        return info;
    }
}