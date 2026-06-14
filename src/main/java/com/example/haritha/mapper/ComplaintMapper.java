package com.example.haritha.mapper;

import com.example.haritha.dto.ComplaintRequest;
import com.example.haritha.dto.ComplaintResponse;
import com.example.haritha.model.Complaint;

import java.time.LocalDateTime;

public class ComplaintMapper {

    // =========================
    // DTO → ENTITY
    // =========================
    public static Complaint toEntity(ComplaintRequest dto) {

        Complaint c = new Complaint();

        c.setUserId(dto.getUserId());
        c.setComplaintType(dto.getComplaintType());
        c.setDescription(dto.getDescription());
        c.setLatitude(dto.getLatitude());
        c.setLongitude(dto.getLongitude());

        c.setStatus("PENDING");
        c.setCreatedAt(LocalDateTime.now());
        c.setUpdatedAt(LocalDateTime.now());

        return c;
    }

    // =========================
    // ENTITY → DTO
    // =========================
    public static ComplaintResponse toDTO(Complaint c) {

        ComplaintResponse dto = new ComplaintResponse();

        dto.setId(c.getId());
        dto.setUserId(c.getUserId());
        dto.setComplaintType(c.getComplaintType());
        dto.setDescription(c.getDescription());
        dto.setLatitude(c.getLatitude());
        dto.setLongitude(c.getLongitude());

        dto.setStatus(c.getStatus());
        dto.setAssignedTo(c.getAssignedTo());
        dto.setCreatedAt(c.getCreatedAt());
        dto.setUpdatedAt(c.getUpdatedAt());

        return dto;
    }
}