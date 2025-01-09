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
) {

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Map<String,Object> getProfile() {
        return profile;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
