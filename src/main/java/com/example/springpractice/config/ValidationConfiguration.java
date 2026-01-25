package com.example.springpractice.config;

import com.example.springpractice.security.ClerkValidator;
import com.example.springpractice.security.JwtValidator;
import com.example.springpractice.security.TokenValidator;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Configuration class to define TokenValidator beans based on application properties
@Configuration
@Slf4j
public class ValidationConfiguration {
    // Define TokenValidator bean based on auth.validator.type property
    @Bean
    // Inject the jwt.secret property with a default value, if auth.validator.type is "skill-mentor"
    @ConditionalOnProperty(name = "auth.validator.type", havingValue = "skill-mentor")

    public TokenValidator jwtTokenValidator(@Value("${jwt.secret:my-secret-key-must-be-at-least-32-characters-long-for-HS256}") String jwtSecret) {
        log.info("JWT validator configured as primary TokenValidator");
        return new JwtValidator(jwtSecret);
    }

    // Define TokenValidator bean based on auth.validator.type property
    @Bean
    // Inject the clerk.jwks.url property, if auth.validator.type is "clerk" or missing
    @ConditionalOnProperty(name = "auth.validator.type", havingValue = "clerk", matchIfMissing = true)
    public TokenValidator clerkTokenValidator(@Value("${clerk.jwks.url}") String clerkJwksUrl) {
        log.info("Clerk validator configured as primary TokenValidator");
        return new ClerkValidator(clerkJwksUrl);
    }
}
