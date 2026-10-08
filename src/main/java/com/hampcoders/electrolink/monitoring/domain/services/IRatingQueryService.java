package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllRatingsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingsByRequestIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingsByTechnicianIdQuery;

import java.util.List;
import java.util.Optional;

public interface IRatingQueryService {
  List<Rating> handle(GetAllRatingsQuery query);
  Optional<Rating> handle(GetRatingByIdQuery query);
  List<Rating> handle(GetRatingsByTechnicianIdQuery query);
  List<Rating> handle(GetRatingsByRequestIdQuery query);
}

