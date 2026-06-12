package com.example.haritha.controller; // This class belongs to the controller package

import com.example.haritha.dto.LoginRequest; // DTO used to receive login data
import com.example.haritha.dto.LoginResponse; // DTO used to send login response
import com.example.haritha.dto.RegisterRequest; // DTO used to receive registration data
import com.example.haritha.service.UserService; // Service containing user-related logic

import org.springframework.beans.factory.annotation.Autowired; // Allows Spring to inject objects automatically
import org.springframework.web.bind.annotation.*; // Imports Spring REST API annotations

@RestController // Marks this class as a REST API controller
@RequestMapping("/auth") // All URLs in this class start with /auth
public class UserController {

    @Autowired // Spring automatically creates and injects UserService
    private UserService userService; // Object used to call user-related business logic

    // REGISTER USER

    @PostMapping("/register") // Handles POST requests to /auth/register
    public String register(@RequestBody RegisterRequest req) { // Converts JSON request into RegisterRequest object
        return userService.register(req); // Calls service method and returns result
    }

    // LOGIN USER

    @PostMapping("/login") // Handles POST requests to /auth/login
    public LoginResponse login(@RequestBody LoginRequest req) { // Converts JSON request into LoginRequest object
        return userService.login(req); // Calls service method and returns login response
    }
}