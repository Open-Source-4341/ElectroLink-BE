package com.hampcoders.electrolink.assets.domain.model.valueobjects;

public record ComponentTypeId(int id) {

    public ComponentTypeId {
        if (id <= 0) {
            throw new IllegalArgumentException("Component type ID must be a positive number.");
        }
    }

    public ComponentTypeId() {
        this(0);
    }
}
