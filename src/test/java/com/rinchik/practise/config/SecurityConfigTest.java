package com.rinchik.practise.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {
    @Test
    void passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String password = "admin";
        String hash = "$2a$10$vKvXkGLX85SeZLZ7U5E6guFqsU6Y1IYUQFyOEeGWgyBqr/2ZQ9K4O";
        assertTrue(encoder.matches(password, hash));
    }
}