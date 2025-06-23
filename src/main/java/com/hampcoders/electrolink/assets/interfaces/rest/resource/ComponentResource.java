package com.hampcoders.electrolink.assets.interfaces.rest.resource;

public record ComponentResource(
        String id,
        String name,
        String description,
        boolean isActive,
        Long componentTypeId
) {
}