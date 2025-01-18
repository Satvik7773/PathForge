package com.example.pathforge.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;

    public List<Users> findAllUsers() {
        return repo.findAll();
    }

    public Users getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    public void createUser(Users newUser) {
        repo.save(newUser);
    }
}
