package com.example.haritha.repository;

import com.example.haritha.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // 🟢 get complaints of a specific user (citizen)
    List<Complaint> findByUserId(Long userId);
}