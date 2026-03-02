package com.example.newsportal.model;

public enum Category {
    SPORTS("Sports"),
    DAILY("Daily News"),
    CRIME("Crime"),
    KNOWLEDGE("Knowledge");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
