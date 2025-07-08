package com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
