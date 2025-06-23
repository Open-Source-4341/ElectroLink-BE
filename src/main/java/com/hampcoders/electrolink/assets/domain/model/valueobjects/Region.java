package com.hampcoders.electrolink.assets.domain.model.valueobjects;

public record Region(String name) {

    public Region {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Region name cannot be null or empty");
        }

    }

    public Region() {
        this("");
    }
}
