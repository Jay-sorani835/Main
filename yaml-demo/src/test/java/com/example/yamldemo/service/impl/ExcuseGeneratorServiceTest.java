package com.example.yamldemo.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExcuseGeneratorServiceTest {

    private final ExcuseGeneratorService excuseGeneratorService = new ExcuseGeneratorService();

    @Test
    void generateExcuse_shouldReturnNonEmptyString() {
        // Act
        String excuse = excuseGeneratorService.generateExcuse();

        // Assert
        assertNotNull(excuse);
        assertTrue(excuse.length() > 0);
    }
}
