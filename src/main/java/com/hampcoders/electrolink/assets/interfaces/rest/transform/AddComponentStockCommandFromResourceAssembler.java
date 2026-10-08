package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.commands.AddComponentStockCommand;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.AddComponentStockResource;

import java.util.UUID;

public class AddComponentStockCommandFromResourceAssembler {
    public static AddComponentStockCommand toCommandFromResource(Long technicianId, AddComponentStockResource resource) {
        return new AddComponentStockCommand(
                technicianId,
                resource.componentId(),
                resource.quantity(),
                resource.alertThreshold()
        );
    }
}