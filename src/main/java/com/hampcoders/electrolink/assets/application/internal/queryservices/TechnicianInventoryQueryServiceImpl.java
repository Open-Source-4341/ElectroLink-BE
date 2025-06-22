package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoriesWithLowStockQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoryByTechnicianIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetStockItemDetailsQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryQueryService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentStockRepository;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.TechnicianInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianInventoryQueryServiceImpl implements TechnicianInventoryQueryService {

    private final TechnicianInventoryRepository technicianInventoryRepository;
    private final ComponentStockRepository componentStockRepository;

    public TechnicianInventoryQueryServiceImpl(TechnicianInventoryRepository technicianInventoryRepository, ComponentStockRepository componentStockRepository) {
        this.technicianInventoryRepository = technicianInventoryRepository;
        this.componentStockRepository = componentStockRepository;
    }

    @Override
    public Optional<TechnicianInventory> handle(GetInventoryByTechnicianIdQuery query) {
        return technicianInventoryRepository.findByTechnicianId(query.technicianId());
    }

    @Override
    public List<TechnicianInventory> handle(GetInventoriesWithLowStockQuery query) {
        return technicianInventoryRepository.findInventoriesWithLowStock(query.threshold());
    }

    @Override
    public Optional<ComponentStock> handle(GetStockItemDetailsQuery query) {
        ComponentId componentId = query.componentId();

        return componentStockRepository
                .findByTechnicianInventory_IdAndComponent_Id(
                        query.technicianId().technicianId(),
                        componentId);
    }

}