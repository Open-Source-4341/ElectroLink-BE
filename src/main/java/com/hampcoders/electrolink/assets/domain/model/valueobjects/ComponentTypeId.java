package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import java.io.Serializable;


public record ComponentTypeId(Long id) implements Serializable {
    public ComponentTypeId {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Component type ID must be a positive number.");
        }
    }
}