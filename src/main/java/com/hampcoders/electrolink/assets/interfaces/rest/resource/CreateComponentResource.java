package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateComponentResource(
        @NotBlank
        String name,
        String description,
        @NotNull
        @Min(1)
        Long componentTypeId,
        Boolean isActive
) {
}

