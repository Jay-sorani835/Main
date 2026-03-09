package com.example.yamldemo.dto;

public class GamificationResponse {
    private int xpGained;
    private int newTotalXp;
    private boolean levelUp;
    private int currentLevel;
    private String message;

    // 1. Private constructor - standard for the Builder pattern
    private GamificationResponse(GamificationResponseBuilder builder) {
        this.xpGained = builder.xpGained;
        this.newTotalXp = builder.newTotalXp;
        this.levelUp = builder.levelUp;
        this.currentLevel = builder.currentLevel;
        this.message = builder.message;
    }

    // Default constructor for JSON deserializers (like Jackson)
    public GamificationResponse() {}

    // 2. Static Inner Builder Class
    public static class GamificationResponseBuilder {
        private int xpGained;
        private int newTotalXp;
        private boolean levelUp;
        private int currentLevel;
        private String message;

        public GamificationResponseBuilder xpGained(int xpGained) {
            this.xpGained = xpGained;
            return this;
        }

        public GamificationResponseBuilder newTotalXp(int newTotalXp) {
            this.newTotalXp = newTotalXp;
            return this;
        }

        public GamificationResponseBuilder levelUp(boolean levelUp) {
            this.levelUp = levelUp;
            return this;
        }

        public GamificationResponseBuilder currentLevel(int currentLevel) {
            this.currentLevel = currentLevel;
            return this;
        }

        public GamificationResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        // 3. The build method
        public GamificationResponse build() {
            return new GamificationResponse(this);
        }
    }

    // 4. Entry point
    public static GamificationResponseBuilder builder() {
        return new GamificationResponseBuilder();
    }

    // Getters (Required for JSON serialization)
    public int getXpGained() { return xpGained; }
    public int getNewTotalXp() { return newTotalXp; }
    public boolean isLevelUp() { return levelUp; }
    public int getCurrentLevel() { return currentLevel; }
    public String getMessage() { return message; }
    
    // Setters (Optional for DTOs, but good for completeness)
    public void setXpGained(int xpGained) { this.xpGained = xpGained; }
    public void setNewTotalXp(int newTotalXp) { this.newTotalXp = newTotalXp; }
    public void setLevelUp(boolean levelUp) { this.levelUp = levelUp; }
    public void setCurrentLevel(int currentLevel) { this.currentLevel = currentLevel; }
    public void setMessage(String message) { this.message = message; }
}