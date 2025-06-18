package com.hampcoders.electrolink.assets.domain.model.valueobjects;

public record TechnicianId(Long technicianId) {
    public TechnicianId {
        if(technicianId < 0 ) {
            throw new IllegalArgumentException("Technician ID must be a positive number.");
        }
    }
    public TechnicianId() {
        this(0L);
    }
}
