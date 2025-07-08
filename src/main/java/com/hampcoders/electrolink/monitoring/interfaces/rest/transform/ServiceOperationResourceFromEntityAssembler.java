package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ServiceOperationResource;

public class ServiceOperationResourceFromEntityAssembler {

  public static ServiceOperationResource toResourceFromEntity(ServiceOperation entity) {
    return new ServiceOperationResource(
        entity.getRequestId().getId(),
        entity.getTechnicianId().getId(),
        entity.getStartedAt(),
        entity.getCompletedAt(),
        entity.getCurrentStatus().name()
    );
  }
}