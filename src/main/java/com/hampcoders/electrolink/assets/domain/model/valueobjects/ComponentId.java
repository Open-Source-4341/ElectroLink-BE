package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public record ComponentId(String componentId) {

    public ComponentId {
        if (componentId == null) {
            throw new IllegalArgumentException("Component ID cannot be null");
        }
    }

    public ComponentId() {
        this(UUID.randomUUID().toString());
    }

    public static ComponentId newId() {
        return new ComponentId(UUID.randomUUID().toString());
    }
}