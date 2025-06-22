package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.OwnerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {

    @Query("SELECT p FROM Property p LEFT JOIN FETCH p.photo WHERE p.ownerId = :ownerId")
    List<Property> findByOwnerId(@Param("ownerId") OwnerId ownerId);

    @Query("SELECT p FROM Property p LEFT JOIN FETCH p.photo WHERE p.id = :id")
    Optional<Property> findByIdWithPhoto(@Param("id") UUID id);
}