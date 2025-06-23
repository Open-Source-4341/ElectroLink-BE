package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentTypeCommand;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreateComponentTypeResource;
import org.springframework.stereotype.Component;

@Component
public class CreateComponentTypeCommandFromResourceAssembler {
    public static CreateComponentTypeCommand toCommandFromResource(CreateComponentTypeResource resource) {
        return new CreateComponentTypeCommand(resource.name(), resource.description());
    }
}