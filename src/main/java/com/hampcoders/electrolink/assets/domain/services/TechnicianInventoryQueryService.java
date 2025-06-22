package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoriesWithLowStockQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoryByTechnicianIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetStockItemDetailsQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.StockItem;

import java.util.List;
import java.util.Optional;

public interface TechnicianInventoryQueryService {
    Optional<TechnicianInventory> handle(GetInventoryByTechnicianIdQuery query);
    List<TechnicianInventory> handle(GetInventoriesWithLowStockQuery query);
    Optional<StockItem> handle(GetStockItemDetailsQuery query);
}