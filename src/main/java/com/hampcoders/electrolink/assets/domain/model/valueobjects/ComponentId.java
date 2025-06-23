package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.Embeddable;


public record ComponentId(UUID componentId) implements Serializable {
    public ComponentId {
        if (componentId == null) {
            throw new IllegalArgumentException("Component ID cannot be null");
        }
    }

    public ComponentId() {
        this(UUID.randomUUID());
    }
}