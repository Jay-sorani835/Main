package com.example.yamldemo.model.enums;

public enum TaskDifficulty {
    EASY(10),
    MEDIUM(20),
    HARD(50),
    EPIC(100);

    private final int xpReward;

    TaskDifficulty(int xpReward) {
        this.xpReward = xpReward;
    }

    public int getXpReward() {
        return xpReward;
    }
}
