package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.CreateServiceOperationCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateServiceOperationResource;

import java.time.OffsetDateTime;

public class CreateServiceOperationCommandFromResourceAssembler {

  public static CreateServiceOperationCommand toCommandFromResource(CreateServiceOperationResource resource) {
    return new CreateServiceOperationCommand(
        new RequestId(resource.technicianId()),
        new TechnicianId(resource.technicianId()),
        OffsetDateTime.now(), // startedAt
        null,                 // completedAt
        ServiceStatus.IN_PROGRESS // status por defecto
    );
  }
}