package com.example.haritha.service;

import com.example.haritha.dto.ComplaintRequest;
import com.example.haritha.model.Complaint;
import com.example.haritha.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository repo;

    public String submitComplaint(ComplaintRequest req, Long userId) {

        // 🚨 GPS validation
        if (req.getLatitude() == null || req.getLongitude() == null) {
            return "Location required to submit complaint";
        }

        // create complaint
        Complaint c = new Complaint();

        c.setUserId(userId);
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