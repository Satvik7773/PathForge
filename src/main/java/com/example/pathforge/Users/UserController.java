package com.example.pathforge.Users;

import com.example.pathforge.run.Run;
import com.example.pathforge.run.RunRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
