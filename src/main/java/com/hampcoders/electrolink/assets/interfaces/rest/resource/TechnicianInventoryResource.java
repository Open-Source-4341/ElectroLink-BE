package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import java.util.List;
import java.util.UUID;

public record TechnicianInventoryResource(
        UUID inventoryId,
        Long technicianId,
        List<ComponentStockResource> stock
) {}