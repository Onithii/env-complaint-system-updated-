package com.example.haritha.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Complaint type is required")
    @Size(min = 3, max = 500)
    private String complaintType;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500)
    private String description;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0")
    private Double longitude;
}