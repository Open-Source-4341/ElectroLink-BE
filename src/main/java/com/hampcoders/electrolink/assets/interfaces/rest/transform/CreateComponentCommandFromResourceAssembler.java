package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentCommand;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreateComponentResource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateComponentCommandFromResourceAssembler {
    public static CreateComponentCommand toCommandFromResource(CreateComponentResource resource) {
        UUID componentId = UUID.randomUUID();
        return new CreateComponentCommand(
                componentId,
                resource.name(),
                resource.description(),
                resource.componentTypeId(),
                resource.isActive() != null ? resource.isActive() : true // Default to true if not provided
        );
    }
}