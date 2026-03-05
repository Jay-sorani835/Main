package com.example.yamlintro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "my-company")
public class CompanyConfig {

    private String name;
    private int foundedYear;
    private boolean isActive;
    private List<String> departments;
    private Map<String, String> officeLocations;

    // Standard getters and setters.
    // We are NOT using Lombok here based on your previous IDE issues.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public Map<String, String> getOfficeLocations() {
        return officeLocations;
    }

    public void setOfficeLocations(Map<String, String> officeLocations) {
        this.officeLocations = officeLocations;
    }
}
