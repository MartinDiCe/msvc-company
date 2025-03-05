package com.diceprojects.msvccompany.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration to validate the active profile before application startup.
 */
@Component
public class ProfileConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PostConstruct
    public void validateProfile() {
        if (!"dev".equals(activeProfile)) {
            throw new IllegalStateException("Application must run with 'dev' profile.");
        }
    }
}
