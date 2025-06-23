package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record UpdateComponentResource(
        @NotBlank String name,
        String description,
        Long componentTypeId,
        Boolean isActive
) {}