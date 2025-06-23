package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentResource;

@org.springframework.stereotype.Component
public class ComponentResourceFromEntityAssembler {
    public static ComponentResource toResourceFromEntity(Component entity) {
        return new ComponentResource(
                entity.getComponentUid().toString(),
                entity.getName(),
                entity.getDescription(),
                entity.getIsActive(),
                entity.getComponentTypeId()
        );
    }
}