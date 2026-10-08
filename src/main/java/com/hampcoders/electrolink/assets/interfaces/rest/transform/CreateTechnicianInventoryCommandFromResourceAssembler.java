package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreateTechnicianInventoryResource;

public class CreateTechnicianInventoryCommandFromResourceAssembler {
    public static CreateTechnicianInventoryCommand toCommandFromResource(CreateTechnicianInventoryResource resource) {
        return new CreateTechnicianInventoryCommand(new TechnicianId(resource.technicianId()));
    }
}
