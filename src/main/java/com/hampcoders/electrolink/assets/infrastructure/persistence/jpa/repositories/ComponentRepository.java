package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComponentRepository extends JpaRepository<Component, UUID> {

    List<Component> findByComponentTypeId(Long typeId);
    Optional<Component> findByComponentUid(UUID componentUid);
    boolean existsByName(String name);
    boolean existsByComponentTypeId(Long componentTypeId);
    List<Component> findByComponentUidIn(List<UUID> uuids);
}