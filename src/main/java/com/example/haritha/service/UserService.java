package com.example.haritha.service;

import com.example.haritha.dto.*;
import com.example.haritha.model.User;
import com.example.haritha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // REGISTER
    public String register(RegisterRequest req) {

        // check if user already exists
        if (repo.findByEmail(req.getEmail()) != null) {
            return "Email already exists";
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword()); // later hash this
        user.setPhone(req.getPhone());

        user.setRole("CITIZEN");
        user.setActive(true);

        repo.save(user);

        return "User registered successfully";
    }

    // LOGIN
    public String login(LoginRequest req) {

        User user = repo.findByEmail(req.getEmail());

        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(req.getPassword())) {
            return "Wrong password";
        }

        return "Login successful";
    }
}