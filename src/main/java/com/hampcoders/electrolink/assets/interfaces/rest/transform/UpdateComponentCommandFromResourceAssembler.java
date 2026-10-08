package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentCommand;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.UpdateComponentResource;

import java.util.UUID;

public class UpdateComponentCommandFromResourceAssembler {
    public static UpdateComponentCommand toCommandFromResource(Long componentId, UpdateComponentResource resource) {
        return new UpdateComponentCommand(
                componentId,
                resource.name(),
                resource.description(),
                resource.componentTypeId(),
                resource.isActive()
        );
    }
}