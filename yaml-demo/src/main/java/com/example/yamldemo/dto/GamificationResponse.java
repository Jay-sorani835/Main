package com.example.yamldemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamificationResponse {
	private int xpGained;
	private int newTotalXp;
	private boolean levelUp;
	private int currentLevel;
	private String message;

	public int getXpGained() {
		return xpGained;
	}

	public void setXpGained(int xpGained) {
		this.xpGained = xpGained;
	}

	public int getNewTotalXp() {
		return newTotalXp;
	}

	public void setNewTotalXp(int newTotalXp) {
		this.newTotalXp = newTotalXp;
	}

	public boolean isLevelUp() {
		return levelUp;
	}

	public void setLevelUp(boolean levelUp) {
		this.levelUp = levelUp;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static class Builder {
		private int xpGained;
		private int newTotalXp;
		private boolean levelUp;
		private int currentLevel;
		private String message;

		public Builder xpGained(int xpGained) {
			this.xpGained = xpGained;
			return this;
		}

		public Builder newTotalXp(int newTotalXp) {
			this.newTotalXp = newTotalXp;
			return this;
		}

		public Builder levelUp(boolean levelUp) {
			this.levelUp = levelUp;
			return this;
		}

		public Builder currentLevel(int currentLevel) {
			this.currentLevel = currentLevel;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public GamificationResponse build() {
			GamificationResponse response = new GamificationResponse();
			response.setXpGained(this.xpGained);
			response.setNewTotalXp(this.newTotalXp);
			response.setLevelUp(this.levelUp);
			response.setCurrentLevel(this.currentLevel);
			response.setMessage(this.message);
			return response;
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}
