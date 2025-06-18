package com.hampcoders.electrolink.assets.domain.model.valueobjects;

public record OwnerId(long ownerId) {
    public OwnerId {
        if (ownerId < 0) {
            throw new IllegalArgumentException("Owner ID must be a positive number.");
        }
    }

    public OwnerId() {
        this(0L);
    }
    
}
