package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.RatingResource;

public class RatingResourceFromEntityAssembler {

  public static RatingResource toResourceFromEntity(Rating entity) {
    return new RatingResource(
        entity.getRatingId(),
        entity.getRequestId().getId(),
        entity.getScore(),
        entity.getComment(),
        entity.getRaterId(),
        entity.getTechnicianId().getId()
    );
  }
}
