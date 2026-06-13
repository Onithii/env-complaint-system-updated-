package com.example.haritha.controller;

import com.example.haritha.model.Complaint;
import com.example.haritha.service.ComplaintService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {



    private ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping
    public Complaint create(@RequestBody Complaint c) {
        System.out.println("DEBUG RECEIVED: " + c.getComplaintType());
        return service.create(c);
    }

    /*// CREATE
    @PostMapping
    public Complaint create(@Valid @RequestBody Complaint c) {
        return service.create(c);
    }*/

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

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Complaint deleted successfully";
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