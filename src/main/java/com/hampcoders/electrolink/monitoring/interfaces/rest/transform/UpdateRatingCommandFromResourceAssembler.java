package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RatingId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateRatingResource;

public class UpdateRatingCommandFromResourceAssembler {
  public static UpdateRatingCommand toCommandFromResource(UpdateRatingResource resource) {
    return new UpdateRatingCommand(
        new RatingId(resource.ratingId()),
        resource.score(),
        resource.comment()
    );
  }
}