package com.example.haritha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintResponse {

    private Long id;

    private Long userId;
    private String complaintType;
    private String description;
    private Double latitude;
    private Double longitude;

    private String status;
    private Long assignedTo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}