package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateRatingResource;

public class CreateRatingCommandFromResourceAssembler {

  public static AddRatingCommand toCommandFromResource(CreateRatingResource resource) {
    return new AddRatingCommand(
        new RequestId(resource.requestId()),
        resource.score(),
        resource.comment(),
        resource.raterId(),
        new TechnicianId(resource.technicianId())
    );
  }
}