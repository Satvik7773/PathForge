package com.example.pathforge.Users;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

// Define a record for the User class
public record User(
        UUID id,
        String username,
        String email,
        String password,
        Map<String, Object> profile,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
