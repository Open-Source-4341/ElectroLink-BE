package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComponentStockRepository extends JpaRepository<ComponentStock, UUID> {

    Optional<ComponentStock> findByTechnicianInventory_IdAndComponent_Id(UUID technicianInventoryId, ComponentId componentId);

    List<ComponentStock> findAllByTechnicianInventory_Id(UUID technicianInventoryId);

    List<ComponentStock> findAllByComponent_Id(ComponentId componentId);

    List<ComponentStock> findAllByQuantityAvailableLessThan(int quantity);

    @Query("SELECT cs FROM ComponentStock cs WHERE cs.quantityAvailable < cs.alertThreshold")
    List<ComponentStock> findStockBelowAlertThreshold();

}