package com.example.pathforge.Users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@Repository
public class UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        UUID id = (UUID) rs.getObject("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String profileJson = rs.getString("profile");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> profile = null;

        try {
            profile = objectMapper.readValue(profileJson, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        if (rs.getTimestamp("created_at") != null) {
            createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        }

        if (rs.getTimestamp("updated_at") != null) {
            updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        }

        return new User(id, username, email, password, profile, createdAt, updatedAt);
    };

    public List<User> findAllUsers() {
        return jdbcClient.sql("SELECT * FROM USERS")
                .query(userRowMapper)
                .list();
    }

    public List<User> getUserByUsername(String username, String password) {
        try {
            List<User> users = jdbcClient.sql("SELECT * FROM USERS WHERE username = ?")
                    .param(1, username)
                    .query(userRowMapper)
                    .list();

            if (users.isEmpty()) {
                return Collections.emptyList(); // Return empty list if no user found
            }

            // Verify the password
            User user = users.getFirst();
            if (!Objects.equals(password, user.password())) {
                return Collections.emptyList(); // Invalid password
            }

            return users;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(UserRepository.class.getName());
            logger.severe("Error occurred while fetching user: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void CreateUser(User newUser) {
        System.out.println("Inserting User: " + newUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String profileJson = null;

        try {
            profileJson = objectMapper.writeValueAsString(newUser.profile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Hash the password before storing

        jdbcClient.sql("INSERT INTO USERS (id, username, email, password, profile, created_at, updated_at) " +
                        "VALUES (?, ?, ?, ?, ?::jsonb, ?, ?)")
                .param(newUser.id())
                .param(newUser.username())
                .param(newUser.email())
                .param(newUser.password()) // Store hashed password
                .param(profileJson)
                .param(newUser.createdAt())
                .param(newUser.updatedAt())
                .update();
    }
}
