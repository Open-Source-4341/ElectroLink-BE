package com.hampcoders.electrolink.assets.domain.model.valueobjects;


import java.util.UUID;

public record OwnerId(UUID ownerId) {
    public OwnerId {
        if(ownerId == null ) {
            throw new IllegalArgumentException("Owner ID must be a positive number.");
        }
    }
    public OwnerId() {
        this(UUID.randomUUID());
    }
}