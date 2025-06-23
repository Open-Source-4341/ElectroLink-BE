package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.commands.UpdatePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.Address;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.District;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.OwnerId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.Region;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.UpdatePropertyResource;

import java.util.UUID;

public class UpdatePropertyCommandFromResourceAssembler {
    public static UpdatePropertyCommand toCommandFromResource(UUID propertyId, UpdatePropertyResource resource) {
        var address = new Address(
                resource.address().street(),
                resource.address().number(),
                resource.address().city(),
                resource.address().postalCode(),
                resource.address().country(),
                resource.address().latitude(),
                resource.address().longitude()
        );

        var region = new Region(resource.region());
        var district = new District(resource.district());

        return new UpdatePropertyCommand(propertyId, address, region, district);
    }

    private UpdatePropertyCommandFromResourceAssembler() {
    }
}