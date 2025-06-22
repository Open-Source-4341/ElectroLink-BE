package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import java.util.UUID;

public record ComponentId(UUID componentId) {
    public ComponentId {
        if (componentId == null) {
            throw new IllegalArgumentException("Component ID cannot be null");
        }
    }

    public ComponentId() {
        this(UUID.randomUUID());
    }

    public static ComponentId newId() {
        return new ComponentId(UUID.randomUUID());
    }
}