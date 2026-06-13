package com.example.haritha.service;

import com.example.haritha.model.Complaint;
import com.example.haritha.exception.ResourceNotFoundException;
import com.example.haritha.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ComplaintService {
    private final ComplaintRepository repo;

    public ComplaintService(ComplaintRepository repo) {
        this.repo = repo;
    }
    // CREATE
    public Complaint create(Complaint r) {
        return repo.save(r);
    }

    // READ ALL
    public List<Complaint> getAll() {
        return repo.findAll();
    }

    // READ BY ID
    public Complaint getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + id));
    }

    // UPDATE
    public Complaint update(Long id, Complaint newData) {

        Complaint existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + id));

        existing.setComplaintType(newData.getComplaintType());
        existing.setDescription(newData.getDescription());
        existing.setLatitude(newData.getLatitude());
        existing.setLongitude(newData.getLongitude());
        existing.setStatus(newData.getStatus());

        return repo.save(existing);
    }

    // DELETE
    public void delete(Long id) {

        Complaint existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + id));

        repo.delete(existing);
    }




    /*@Autowired
    private ComplaintRepository repo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // 🟢 SUBMIT COMPLAINT
    public String submitComplaint(ComplaintRequest req,
                                  HttpServletRequest request) {

        User user = getUserFromRequest(request);

        Complaint c = new Complaint();

        c.setUserId(user.getId());
        c.setComplaintType(req.getComplaintType());
        c.setDescription(req.getDescription());
        c.setLatitude(req.getLatitude());
        c.setLongitude(req.getLongitude());

        c.setStatus("PENDING");

        repo.save(c);

        return "Complaint submitted successfully";
    }

    // 🟢 GET MY COMPLAINTS
    public List<Complaint> getMyComplaints(HttpServletRequest request) {

        User user = getUserFromRequest(request);

        return repo.findByUserId(user.getId());
    }

    // 🟢 GET ALL COMPLAINTS
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    // 🔐 JWT EXTRACTION (SAFE + CLEAN)
    private User getUserFromRequest(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        // 🛑 FIX: safer null + format check
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        // 🛑 FIX: token safety check (important)
        if (token.isEmpty()) {
            throw new RuntimeException("Token is empty");
        }

        String email = jwtUtil.extractEmail(token);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }*/
}