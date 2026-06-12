package com.example.haritha.service; // This class belongs to the service package

import com.example.haritha.dto.LoginRequest; // DTO used to receive login data
import com.example.haritha.dto.LoginResponse; // DTO used to send login response
import com.example.haritha.dto.RegisterRequest; // DTO used to receive registration data
import com.example.haritha.model.User; // User entity (database table)
import com.example.haritha.repository.UserRepository; // Repository used to access database
import com.example.haritha.util.JwtUtil; // Utility class for generating JWT tokens

import org.springframework.beans.factory.annotation.Autowired; // Allows Spring to inject objects automatically
import org.springframework.stereotype.Service; // Marks this class as a Service

@Service // Tells Spring this class contains business logic
public class UserService {

    @Autowired // Spring automatically creates and injects UserRepository
    private UserRepository repo; // Used to save and retrieve users

    @Autowired // Spring automatically creates and injects JwtUtil
    private JwtUtil jwtUtil; // Used to generate JWT tokens

    // REGISTER

    public String register(RegisterRequest req) { // Method that handles user registration

        User user = new User(); // Create a new User object

        user.setUsername(req.getUsername()); // Copy username from DTO to User object
        user.setFullName(req.getFullName()); // Copy full name from DTO to User object
        user.setEmail(req.getEmail()); // Copy email from DTO to User object
        user.setPassword(req.getPassword()); // Copy password from DTO to User object
        user.setPhone(req.getPhone()); // Copy phone number from DTO to User object

        user.setRole("CITIZEN"); // Give every new user the CITIZEN role
        user.setActive(true); // Mark account as active

        repo.save(user); // Save user to database

        return "User registered successfully"; // Return success message
    }

    // LOGIN

    public LoginResponse login(LoginRequest req) { // Method that handles login

        User user = repo.findByEmail(req.getEmail()); // Find user using email

        if (user == null) { // Check if user exists
            throw new RuntimeException("User not found"); // Stop and show error
        }

        if (!user.getPassword().equals(req.getPassword())) { // Check password matches
            throw new RuntimeException("Wrong password"); // Stop and show error
        }

        String token = jwtUtil.generateToken(user.getEmail()); // Generate JWT token

        return new LoginResponse(token); // Return token inside LoginResponse DTO
    }
}