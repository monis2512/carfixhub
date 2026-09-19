package com.shahrukh.autocare;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "car_queries")
public class CarQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String carModel;

    @Column(nullable = false)
    private String serviceType;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false, length = 20)
    private String mobileNumber;

    @Column(length = 500)
    private String photoName;

    @Column(nullable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String v) {
        carModel = v;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String v) {
        serviceType = v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String v) {
        description = v;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String v) {
        mobileNumber = v;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String v) {
        photoName = v;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant v) {
        createdAt = v;
    }
}
