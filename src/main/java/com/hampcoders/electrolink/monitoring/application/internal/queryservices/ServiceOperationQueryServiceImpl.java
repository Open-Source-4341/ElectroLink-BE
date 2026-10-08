package com.hampcoders.electrolink.monitoring.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllServiceOperationsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationsByTechnicianIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.services.IServiceOperationQueryService;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ServiceOperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOperationQueryServiceImpl implements IServiceOperationQueryService {

  private final ServiceOperationRepository serviceOperationRepository;

  public ServiceOperationQueryServiceImpl(ServiceOperationRepository serviceOperationRepository) {
    this.serviceOperationRepository = serviceOperationRepository;
  }

  @Override
  public List<ServiceOperation> handle(GetAllServiceOperationsQuery query) {
    return serviceOperationRepository.findAll();
  }

  @Override
  public Optional<ServiceOperation> handle(GetServiceOperationByIdQuery query) {
    return serviceOperationRepository.findByRequestId(new RequestId(query.requestId()));
  }

  @Override
  public List<ServiceOperation> handle(GetServiceOperationsByTechnicianIdQuery query) {
    return serviceOperationRepository.findByTechnicianId(new TechnicianId(query.technicianId()));
  }
}