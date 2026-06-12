package com.example.haritha.controller;

import com.example.haritha.dto.ComplaintRequest;
import com.example.haritha.service.ComplaintService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // 🟢 Submit complaint (JWT required)
    @PostMapping("/submit")
    public String submitComplaint(@RequestBody ComplaintRequest req,
                                  HttpServletRequest request) {

        return complaintService.submitComplaint(req, request);
    }
}