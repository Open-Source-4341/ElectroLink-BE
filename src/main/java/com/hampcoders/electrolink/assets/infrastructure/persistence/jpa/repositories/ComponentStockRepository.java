package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComponentStockRepository extends JpaRepository<ComponentStock, UUID> {

    @Query("SELECT cs FROM ComponentStock cs WHERE cs.technicianInventory.id = :technicianInventoryId AND cs.component.componentUid = :componentUid")
    Optional<ComponentStock> findByTechnicianInventoryIdAndComponentUid(@Param("technicianInventoryId") Long technicianInventoryId, @Param("componentUid") Long componentUid);

    List<ComponentStock> findAllByTechnicianInventory_Id(UUID technicianInventoryId);

    List<ComponentStock> findAllByComponent_ComponentUid(Long componentUid);

    List<ComponentStock> findAllByQuantityAvailableLessThan(int quantity);

    @Query("SELECT cs FROM ComponentStock cs WHERE cs.quantityAvailable < cs.alertThreshold")
    List<ComponentStock> findStockBelowAlertThreshold();

}