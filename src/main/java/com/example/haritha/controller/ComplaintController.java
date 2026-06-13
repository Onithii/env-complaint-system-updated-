package com.example.haritha.controller;

import com.example.haritha.model.Complaint;
import com.example.haritha.service.ComplaintService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Complaint create(@Valid @RequestBody Complaint c) {
        return service.create(c);
    }

    // READ ALL
    @GetMapping
    public List<Complaint> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Complaint getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Complaint update(@PathVariable Long id, @RequestBody Complaint r) {
        return service.update(id, r);
    }

    /*// 🟢 Submit complaint (JWT required)
    @PostMapping("/submit")
    public String submitComplaint(@RequestBody ComplaintRequest req,
                                  HttpServletRequest request) {
        return complaintService.submitComplaint(req, request);
    }

    // 🟢 GET MY COMPLAINTS (JWT-based)
    @GetMapping("/my")
    public List<Complaint> getMyComplaints(HttpServletRequest request) {
        return complaintService.getMyComplaints(request);
    }

    // 🟢 GET ALL COMPLAINTS (admin/authority later)
    @GetMapping("/all")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }*/
}