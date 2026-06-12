package com.example.haritha.controller;

import com.example.haritha.dto.ComplaintRequest;
import com.example.haritha.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    // userId will come later from login session/JWT
    @PostMapping("/submit/{userId}")
    public String submit(
            @RequestBody ComplaintRequest req,
            @PathVariable Long userId
    ) {
        return service.submitComplaint(req, userId);
    }
}