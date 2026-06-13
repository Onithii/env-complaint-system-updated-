package com.example.haritha.service;

import com.example.haritha.dto.LoginRequest;
import com.example.haritha.dto.LoginResponse;
import com.example.haritha.dto.RegisterRequest;
import com.example.haritha.model.User;
import com.example.haritha.repository.UserRepository;
import com.example.haritha.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    // 🟢 REGISTER USER
    public String register(RegisterRequest req) {

        User existingUser = repo.findByEmail(req.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setUsername(req.getUsername());

        repo.save(user);

        return "User registered successfully";
    }

    // 🟢 LOGIN USER (DEBUG VERSION ADDED)
    public LoginResponse login(LoginRequest req) {

        System.out.println("LOGIN START");
        System.out.println("EMAIL: " + req.getEmail());

        User user = repo.findByEmail(req.getEmail());

        System.out.println("USER FOUND: " + (user != null));

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (user.getPassword() == null ||
                !user.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}