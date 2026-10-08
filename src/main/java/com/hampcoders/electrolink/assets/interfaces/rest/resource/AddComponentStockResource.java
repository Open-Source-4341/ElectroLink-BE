package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddComponentStockResource(
        @NotNull Long componentId,
        @Min(1) int quantity,
        @Min(0) int alertThreshold
) {}