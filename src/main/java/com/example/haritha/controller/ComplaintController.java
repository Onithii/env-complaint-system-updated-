package com.example.haritha.controller;

import com.example.haritha.dto.ComplaintRequest;
import com.example.haritha.dto.ComplaintResponse;
import com.example.haritha.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    // =======================
    // CREATE COMPLAINT
    // =======================
    @PostMapping
    public ComplaintResponse create(
            @Valid @RequestBody ComplaintRequest dto) {

        return service.create(dto);
    }

    // =======================
    // GET ALL COMPLAINTS
    // =======================
    @GetMapping
    public List<ComplaintResponse> getAll() {
        return service.getAll();
    }

    // =======================
    // GET BY ID
    // =======================
    @GetMapping("/{id}")
    public ComplaintResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // =======================
    // UPDATE COMPLAINT
    // =======================
    @PutMapping("/{id}")
    public ComplaintResponse update(
            @PathVariable Long id,
            @RequestBody ComplaintRequest dto) {

        return service.update(id, dto);
    }

    // =======================
    // DELETE COMPLAINT
    // =======================
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);
        return "Complaint deleted successfully";
    }
}