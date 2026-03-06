package com.example.yamldemo.service.impl;

import com.example.yamldemo.dto.GamificationResponse;
import com.example.yamldemo.model.User;
import com.example.yamldemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GamificationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GamificationService gamificationService;

    @Test
    void addXp_shouldIncreaseXpWithoutLevelUp() {
        // Arrange
        User user = new User();
        user.setXp(50);
        user.setLevel(1);
        int xpToAdd = 20;

        // Act
        GamificationResponse response = gamificationService.addXp(user, xpToAdd);

        // Assert
        assertEquals(70, response.getNewTotalXp());
        assertEquals(1, response.getCurrentLevel());
        assertFalse(response.isLevelUp());
        assertEquals("You gained 20 XP!", response.getMessage());
        assertEquals(70, user.getXp());
        assertEquals(1, user.getLevel());
        verify(userRepository).save(user);
    }

    @Test
    void addXp_shouldIncreaseXpAndLevelUp() {
        // Arrange
        User user = new User();
        user.setXp(80);
        user.setLevel(1);
        int xpToAdd = 50;

        // Act
        GamificationResponse response = gamificationService.addXp(user, xpToAdd);

        // Assert
        assertEquals(130, response.getNewTotalXp());
        assertEquals(2, response.getCurrentLevel());
        assertTrue(response.isLevelUp());
        assertEquals("LEVEL UP! You are now level 2!", response.getMessage());
        assertEquals(130, user.getXp());
        assertEquals(2, user.getLevel());
        verify(userRepository).save(user);
    }
}
