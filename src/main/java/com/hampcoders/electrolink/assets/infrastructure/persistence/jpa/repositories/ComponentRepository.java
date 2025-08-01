package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {

    List<Component> findByComponentTypeId(Long typeId);
    Optional<Component> findByComponentUid(Long componentUid);
    boolean existsByName(String name);
    boolean existsByComponentTypeId(Long componentTypeId);
    List<Component> findByComponentUidIn(List<Long> uuids);
    List<Component> findTop10ByNameContainingIgnoreCase(String nameFragment);
}