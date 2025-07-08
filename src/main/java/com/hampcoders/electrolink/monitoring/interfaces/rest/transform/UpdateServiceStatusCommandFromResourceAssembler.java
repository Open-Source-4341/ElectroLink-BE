package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateServiceStatusCommand;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateServiceStatusResource;

public class UpdateServiceStatusCommandFromResourceAssembler {
  public static UpdateServiceStatusCommand toCommandFromResource(UpdateServiceStatusResource resource) {
    return new UpdateServiceStatusCommand(
        resource.requestId(),
        resource.newStatus()
    );
  }
}