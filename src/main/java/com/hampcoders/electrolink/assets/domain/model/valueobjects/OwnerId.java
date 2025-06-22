package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record OwnerId(UUID ownerId) {
    public OwnerId {
        if (ownerId == null) {
            throw new IllegalArgumentException("Owner ID cannot be null");
        }
    }

    public OwnerId() {
        this(UUID.randomUUID());
    }

    public static OwnerId newId() {
        return new OwnerId(UUID.randomUUID());
    }
}