package com.hampcoders.electrolink.assets.domain.model.valueobjects;

public record District(String name, String ubigeo) {

    public District {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("District name cannot be null or empty");
        }
        if (ubigeo == null || ubigeo.isBlank()) {
            throw new IllegalArgumentException("Ubigeo cannot be null or empty");
        }
    }

    public District() {
        this("", "");
    }
}
