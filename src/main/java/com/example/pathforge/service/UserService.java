package com.example.pathforge.service;

import com.example.pathforge.model.Users;
import com.example.pathforge.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<Users> findAllUsers() {
        return repo.findAll();
    }

    public Users getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    public void register(Users newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        repo.save(newUser);
    }

    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername())  ;
        } else {
            return "fail";
        }
    }
}
