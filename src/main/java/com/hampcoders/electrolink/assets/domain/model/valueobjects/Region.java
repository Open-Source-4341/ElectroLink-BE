package com.hampcoders.electrolink.assets.domain.model.valueobjects;

public record Region(String name, String code) {

    public Region {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Region name cannot be null or empty");
        }
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Region code cannot be null or empty");
        }
    }

    public Region() {
        this("", "");
    }
}
