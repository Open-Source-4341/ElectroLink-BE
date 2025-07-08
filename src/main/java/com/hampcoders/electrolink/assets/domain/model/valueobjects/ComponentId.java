package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import java.io.Serializable;


public record ComponentId(Long componentId) implements Serializable {
    public ComponentId {
        if (componentId == null || componentId <= 0) {
            throw new IllegalArgumentException("Component ID cannot be null");
        }
    }
}