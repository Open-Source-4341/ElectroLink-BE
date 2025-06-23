package com.hampcoders.electrolink.assets.interfaces.rest.transform;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentStockResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.TechnicianInventoryResource;

import java.util.stream.Collectors;

public class TechnicianInventoryResourceFromEntityAssembler {

    public static TechnicianInventoryResource toResourceFromEntity(TechnicianInventory entity) {
        var stockResources = entity.getComponentStocks().stream()
                .map(TechnicianInventoryResourceFromEntityAssembler::toStockResourceFromEntity)
                .collect(Collectors.toList());

        return new TechnicianInventoryResource(
                entity.getId(),
                entity.getTechnicianId(),
                stockResources
        );
    }

    private static ComponentStockResource toStockResourceFromEntity(ComponentStock stockEntity) {
        return new ComponentStockResource(
                stockEntity.getId(),
                stockEntity.getComponent().getComponentUid(),
                stockEntity.getComponent().getName(),
                stockEntity.getQuantityAvailable(),
                stockEntity.getAlertThreshold(),
                stockEntity.getLastUpdated()
        );
    }
}