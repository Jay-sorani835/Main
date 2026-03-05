package com.example.yamlintro.controller;

import com.example.yamlintro.config.CompanyConfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intro")
public class YamlDemoController {

    private final CompanyConfig companyConfig;

    
    public YamlDemoController(CompanyConfig companyConfig) {
        this.companyConfig = companyConfig;
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        Map<String, Object> data = new HashMap();
        data.put("name", companyConfig.getName());
        data.put("departments", companyConfig.getDepartments());
        data.put("founded year", companyConfig.getFoundedYear());
        data.put("location", companyConfig.getOfficeLocations());
        return data;
    }
}
