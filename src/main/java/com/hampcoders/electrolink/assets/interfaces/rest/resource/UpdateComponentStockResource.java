package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.constraints.Min;

public record UpdateComponentStockResource(
        @Min(0) int newQuantity,
        @Min(0) int newAlertThreshold
) {}
