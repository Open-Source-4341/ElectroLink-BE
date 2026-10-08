package com.hampcoders.electrolink.assets.domain.model.valueobjects;

public record OwnerId(Long ownerId) {
    public OwnerId {
        if (ownerId == null || ownerId <= 0) {
            throw new IllegalArgumentException("Owner ID must be a positive number.");
        }
    }

}