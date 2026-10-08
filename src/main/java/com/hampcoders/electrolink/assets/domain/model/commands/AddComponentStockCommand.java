package com.hampcoders.electrolink.assets.domain.model.commands;


public record AddComponentStockCommand(
        Long technicianId,
        Long componentId,
        int quantity,
        int alertThreshold
) {}