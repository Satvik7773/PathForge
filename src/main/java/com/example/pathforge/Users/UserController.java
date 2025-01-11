package com.example.pathforge.Users;

import com.example.pathforge.run.Run;
import com.example.pathforge.run.RunRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get-byusername")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username, @RequestParam String password) {
        try {
            List<User> users = userRepository.getUserByUsername(username, password);

            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving user information");
        }
    }

    @GetMapping("/all-user")
    public List<User> getAllUsers() {
           return userRepository.findAllUsers();
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody User newUser) {
        userRepository.CreateUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Added Successfully");
    }
}
