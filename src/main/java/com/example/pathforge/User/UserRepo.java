package com.example.pathforge.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<Users, UUID> {

    Users findByUsername(String username);
}
