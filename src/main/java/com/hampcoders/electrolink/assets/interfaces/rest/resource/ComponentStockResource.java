package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import java.util.Date;
import java.util.UUID;

public record ComponentStockResource(
        UUID componentStockId,
        Long componentId,
        String componentName,
        int quantityAvailable,
        int alertThreshold,
        Date lastUpdated
) {}