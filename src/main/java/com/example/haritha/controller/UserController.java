package com.example.haritha.controller;

import com.example.haritha.dto.LoginRequest;
import com.example.haritha.dto.LoginResponse;
import com.example.haritha.dto.RegisterRequest;
import com.example.haritha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // 🟢 REGISTER USER
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        return userService.register(req);
    }

    // 🟢 LOGIN USER
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return userService.login(req);
    }
}