package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TechnicianInventoryRepository extends JpaRepository<TechnicianInventory, UUID> {

    @Query("SELECT ti FROM TechnicianInventory ti LEFT JOIN FETCH ti.stockList.items WHERE ti.technicianId = :technicianId")
    Optional<TechnicianInventory> findByTechnicianIdWithStocks(@Param("technicianId") Long technicianId);

    Optional<TechnicianInventory> findByTechnicianId(Long technicianId);

    @Query("SELECT DISTINCT i FROM TechnicianInventory i JOIN i.stockList.items s WHERE s.quantityAvailable < :threshold")
    List<TechnicianInventory> findInventoriesWithLowStock(@Param("threshold") int threshold);

    boolean existsByTechnicianId(Long technicianId);
}

