package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import java.util.UUID;

public record TechnicianId(UUID technicianId) {
    public TechnicianId {
        if(technicianId == null ) {
            throw new IllegalArgumentException("Technician ID must be a positive number.");
        }
    }
    public TechnicianId() {
        this(UUID.randomUUID());
    }
}
