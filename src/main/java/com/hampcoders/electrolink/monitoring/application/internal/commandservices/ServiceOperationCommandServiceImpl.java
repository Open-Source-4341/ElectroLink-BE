package com.hampcoders.electrolink.monitoring.application.internal.commandservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.domain.model.commands.CreateServiceOperationCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateServiceStatusCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.services.IServiceOperationCommandService;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ServiceOperationRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ServiceOperationCommandServiceImpl implements IServiceOperationCommandService {

  private final ServiceOperationRepository serviceOperationRepository;

  public ServiceOperationCommandServiceImpl(ServiceOperationRepository serviceOperationRepository) {
    this.serviceOperationRepository = serviceOperationRepository;
  }

  @Override
  public RequestId handle(CreateServiceOperationCommand command) {
    var requestId = command.requestId();

    var serviceOperation = new ServiceOperation(
        requestId,
        command.technicianId(),
        command.startedAt(),
        command.completedAt(),
        command.currentStatus()
    );

    serviceOperationRepository.save(serviceOperation);
    return requestId;
  }

  @Override
  public void handle(UpdateServiceStatusCommand command) {
    var serviceOperation = serviceOperationRepository
        .findByRequestId(new RequestId(command.requestId()))
        .orElseThrow(() -> new IllegalArgumentException("ServiceOperation not found"));

    serviceOperation.updateStatus(ServiceStatus.valueOf(command.newStatus()));

    ServiceStatus newStatus = ServiceStatus.valueOf(command.newStatus());
    serviceOperation.updateStatus(newStatus);

    if (newStatus == ServiceStatus.COMPLETED) {
      serviceOperation.setCompletedAt(OffsetDateTime.now());
    }

    serviceOperationRepository.save(serviceOperation);
  }
}