package com.example.haritha.service;

import com.example.haritha.dto.ComplaintRequest;
import com.example.haritha.dto.ComplaintResponse;
import com.example.haritha.exception.ResourceNotFoundException;
import com.example.haritha.mapper.ComplaintMapper;
import com.example.haritha.model.Complaint;
import com.example.haritha.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository repo;

    public ComplaintService(ComplaintRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public ComplaintResponse create(ComplaintRequest dto) {

        Complaint complaint = ComplaintMapper.toEntity(dto);

        return ComplaintMapper.toDTO(repo.save(complaint));
    }

    // GET ALL
    public List<ComplaintResponse> getAll() {

        return repo.findAll()
                .stream()
                .map(ComplaintMapper::toDTO)
                .toList();
    }

    // GET BY ID
    public ComplaintResponse getById(Long id) {

        Complaint complaint = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + id));

        return ComplaintMapper.toDTO(complaint);
    }

    // UPDATE
    public ComplaintResponse update(Long id, ComplaintRequest dto) {

        Complaint existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + id));

        existing.setComplaintType(dto.getComplaintType());
        existing.setDescription(dto.getDescription());
        existing.setLatitude(dto.getLatitude());
        existing.setLongitude(dto.getLongitude());
        existing.setUpdatedAt(LocalDateTime.now());

        return ComplaintMapper.toDTO(repo.save(existing));
    }

    // DELETE
    public void delete(Long id) {

        Complaint existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + id));

        repo.delete(existing);
    }
}