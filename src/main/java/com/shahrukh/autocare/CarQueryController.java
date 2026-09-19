package com.shahrukh.autocare;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/queries")
public class CarQueryController {

    private final CarQueryRepository repo;

    public CarQueryController(CarQueryRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateQueryRequest r) {

        if (blank(r.getCarModel())
                || blank(r.getServiceType())
                || blank(r.getDescription())
                || blank(r.getMobileNumber())) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message",
                            "Car, service, description and mobile number are required."
                    ));
        }

        String mobile = r.getMobileNumber().replaceAll("\\s+", "");

        if (!mobile.matches("^[6-9][0-9]{9}$")) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message",
                            "Please enter a valid 10-digit Indian mobile number."
                    ));
        }

        CarQuery q = new CarQuery();

        q.setCarModel(r.getCarModel().trim());
        q.setServiceType(r.getServiceType().trim());
        q.setDescription(r.getDescription().trim());
        q.setMobileNumber(mobile);
        q.setPhotoName(
                r.getPhotoName() == null
                        ? ""
                        : r.getPhotoName().trim()
        );

        // Do NOT set createdAt here.
        // CarQuery @PrePersist will automatically use Instant.now().

        CarQuery saved = repo.save(q);

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "id", saved.getId(),
                        "message", "Enquiry submitted successfully."
                )
        );
    }

    private boolean blank(String s) {
        return s == null || s.isBlank();
    }
}
