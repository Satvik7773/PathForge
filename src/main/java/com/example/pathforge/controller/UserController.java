package com.example.pathforge.controller;

import com.example.pathforge.service.UserService;
import com.example.pathforge.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/username")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        try {
            Users users = userService.getUserByUsername(username);

            if (users == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username ");
            }

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving user information");
        }
    }

    @GetMapping("/all-users")
    public List<Users> getAllUsers() {
           return userService.findAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody Users newUser) {
        userService.register(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Added Successfully");
    }
}
