package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentLookupResource;

public final class ComponentLookupResourceFromEntityAssembler {
    public static ComponentLookupResource toResource(Component entity) {
        return new ComponentLookupResource(entity.getComponentUid(), entity.getName());
    }
}