package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.UpdateComponentStockResource;
import java.util.UUID;

public class UpdateComponentStockCommandFromResourceAssembler {
    public static UpdateComponentStockCommand toCommandFromResource(UUID technicianId, UUID componentId, UpdateComponentStockResource resource) {
        return new UpdateComponentStockCommand(
                technicianId,
                componentId,
                resource.newQuantity(),
                resource.newAlertThreshold()
        );
    }
}