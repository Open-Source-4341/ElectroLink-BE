package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentTypeResource;
import org.springframework.stereotype.Component;

@Component
public class ComponentTypeResourceFromEntityAssembler {
    public static ComponentTypeResource toResourceFromEntity(ComponentType entity) {
        return new ComponentTypeResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }
}