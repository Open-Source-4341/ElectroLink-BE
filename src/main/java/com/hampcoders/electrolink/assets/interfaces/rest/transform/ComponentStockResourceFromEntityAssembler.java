package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentStockResource;

import java.util.Date;

public class ComponentStockResourceFromEntityAssembler {

    public static ComponentStockResource toResourceFromEntity(ComponentStock entity) {
        return new ComponentStockResource(
                entity.getId(),
                entity.getComponent().getComponentUid(),
                entity.getComponent().getName(),
                entity.getQuantityAvailable(),
                entity.getAlertThreshold(),
                entity.getLastUpdated()
        );
    }

    private ComponentStockResourceFromEntityAssembler() {
    }
}