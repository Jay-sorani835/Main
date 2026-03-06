package com.example.yamldemo.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ExcuseGeneratorService {

    private final List<String> excuses = List.of(
            "It worked on my machine.",
            "I'm pretty sure that's a feature, not a bug.",
            "The client changed the requirements again.",
            "My code is currently compiling.",
            "I was told to stop working on that and focus on something else.",
            "The coffee machine was broken.",
            "It must be a caching issue.",
            "I thought someone else was handling that.",
            "We didn't have enough time to write tests for that.",
            "You must be using the wrong version of the API.",
            "I haven't had my second cup of coffee yet.",
            "That's a known issue with the underlying framework.",
            "My dog ate my source code.",
            "We'll fix that in the next sprint.",
            "The server was down, I swear!");

    private final Random random = new Random();

    public String generateExcuse() {
        return excuses.get(random.nextInt(excuses.size()));
    }
}
