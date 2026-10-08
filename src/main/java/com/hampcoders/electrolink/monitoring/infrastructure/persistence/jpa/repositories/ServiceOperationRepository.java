package com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceOperationRepository extends JpaRepository<ServiceOperation, Long> {
  Optional<ServiceOperation> findByRequestId(RequestId requestId);
  List<ServiceOperation> findByTechnicianId(TechnicianId technicianId);
}