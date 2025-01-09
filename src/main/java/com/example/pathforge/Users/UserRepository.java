package com.example.pathforge.Users;

import com.example.pathforge.run.Run;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient)
    {
        this.jdbcClient = jdbcClient;
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
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
        // Perform the query and use the custom RowMapper to handle deserialization of the profile field
        return jdbcClient.sql("SELECT * FROM USERS")
                .query(userRowMapper)
                .list();
    }

    public void CreateUser(User newUser) {
        System.out.println("Inserting User: " + newUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String profileJson = null;

        try {
            profileJson = objectMapper.writeValueAsString(newUser.getProfile());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
        }

        // Insert data into the USERS table
        jdbcClient.sql("INSERT INTO USERS (id, username, email, password, profile, created_at, updated_at) " +
                        "VALUES (?, ?, ?, ?, ?::jsonb, ?, ?)")  // Cast profile to jsonb
                .param(newUser.getId())  // Bind the id
                .param(newUser.getUsername())  // Bind the username
                .param(newUser.getEmail())  // Bind the email
                .param(newUser.getPassword())  // Bind the password
                .param(profileJson)  // Bind the serialized profile JSON string
                .param(newUser.getCreatedAt())  // Bind the created_at timestamp
                .param(newUser.getUpdatedAt())  // Bind the updated_at timestamp
                .update();  // Perform the update (insert)
    }
}
