package com.hampcoders.electrolink.assets.domain.model.valueobjects;


public record District(String name) {

    public District {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("District name cannot be null or empty");
        }
    }

    public District() {
        this("");
    }
}
