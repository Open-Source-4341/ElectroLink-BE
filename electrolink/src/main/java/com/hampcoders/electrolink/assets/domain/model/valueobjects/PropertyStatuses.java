package com.hampcoders.electrolink.assets.domain.model.valueobjects;

public enum PropertyStatuses {
    ACTIVE (1),
    DEFAULT (2),
    INACTIVE (3);

    private final int value;

    PropertyStatuses(int value) {
        this.value = value;
    }
}
