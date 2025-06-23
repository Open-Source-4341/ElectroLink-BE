package com.hampcoders.electrolink.assets.domain.model.commands;


import java.util.UUID;

public record AddComponentStockCommand(
        UUID technicianId,
        UUID componentId,
        int quantity,
        int alertThreshold
) {}