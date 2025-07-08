package com.hampcoders.electrolink.monitoring.application.internal.queryservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllRatingsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingsByRequestIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingsByTechnicianIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RatingId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.domain.services.IRatingQueryService;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingQueryServiceImpl implements IRatingQueryService {

  private final RatingRepository ratingRepository;

  public RatingQueryServiceImpl(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }

  @Override
  public List<Rating> handle(GetAllRatingsQuery query) {
    return ratingRepository.findAll();
  }

  @Override
  public Optional<Rating> handle(GetRatingByIdQuery query) {
    return ratingRepository.findById(new RatingId(query.ratingId()));
  }

  @Override
  public List<Rating> handle(GetRatingsByRequestIdQuery query) {
    return ratingRepository.findByRequestId(new RequestId(query.requestId()));
  }

  @Override
  public List<Rating> handle(GetRatingsByTechnicianIdQuery query) {
    return ratingRepository.findByTechnicianId(new TechnicianId(query.technicianId()));
  }
}