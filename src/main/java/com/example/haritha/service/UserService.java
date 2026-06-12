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

    // -------------------------
    // REGISTER
    // -------------------------
    public String register(RegisterRequest req) {

        User user = new User();

        user.setUsername(req.getUsername());
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setPhone(req.getPhone());

        user.setRole("CITIZEN");
        user.setActive(true);

        repo.save(user);

        return "User registered successfully";
    }

    // -------------------------
    // LOGIN
    // -------------------------
    public LoginResponse login(LoginRequest req) {

        User user = repo.findByEmail(req.getEmail());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!user.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}