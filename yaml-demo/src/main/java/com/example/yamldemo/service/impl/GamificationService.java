package com.example.yamldemo.service.impl;

import com.example.yamldemo.dto.GamificationResponse;
import com.example.yamldemo.model.User;
import com.example.yamldemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GamificationService {

	@Autowired
    private UserRepository userRepository;

    @Transactional
    public GamificationResponse addXp(User user, int xpToAdd) {
        int previousLevel = user.getLevel();
        int newXp = user.getXp() + xpToAdd;

        int newLevel = (newXp / 100) + 1;
        boolean leveledUp = newLevel > previousLevel;

        user.setXp(newXp);
        user.setLevel(newLevel);
        userRepository.save(user);

        String message = leveledUp ? "LEVEL UP! You are now level " + newLevel + "!" : "You gained " + xpToAdd + " XP!";

        return GamificationResponse.builder()
                .xpGained(xpToAdd)
                .newTotalXp(newXp)
                .levelUp(leveledUp)
                .currentLevel(newLevel)
                .message(message)
                .build();
    }
}
