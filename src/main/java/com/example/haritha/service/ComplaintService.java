package com.example.haritha.service;

import com.example.haritha.dto.ComplaintRequest;
import com.example.haritha.model.Complaint;
import com.example.haritha.model.User;
import com.example.haritha.repository.ComplaintRepository;
import com.example.haritha.repository.UserRepository;
import com.example.haritha.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository repo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String submitComplaint(ComplaintRequest req,
                                  HttpServletRequest request) {

        // 🔐 Extract token
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);

        // 👤 Extract email
        String email = jwtUtil.extractEmail(token);

        // 👤 Get user
        User user = userRepository.findByEmail(email);

        // 📌 Create complaint
        Complaint c = new Complaint();

        c.setUserId(user.getId());
        c.setComplaintType(req.getComplaintType());
        c.setDescription(req.getDescription());
        c.setLatitude(req.getLatitude());
        c.setLongitude(req.getLongitude());

        c.setStatus("PENDING");
        c.setCreatedAt(LocalDateTime.now());
        c.setUpdatedAt(LocalDateTime.now());

        repo.save(c);

        return "Complaint submitted successfully";
    }
}