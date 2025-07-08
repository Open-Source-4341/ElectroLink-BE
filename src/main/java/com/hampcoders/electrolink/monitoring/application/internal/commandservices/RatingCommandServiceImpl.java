package com.hampcoders.electrolink.monitoring.application.internal.commandservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.domain.model.commands.AddRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.services.IRatingCommandService;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.RatingRepository;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ServiceOperationRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingCommandServiceImpl implements IRatingCommandService {

  private final RatingRepository ratingRepository;
  private final ServiceOperationRepository serviceOperationRepository;

  public RatingCommandServiceImpl(RatingRepository ratingRepository, ServiceOperationRepository serviceOperationRepository) {
    this.ratingRepository = ratingRepository;
    this.serviceOperationRepository = serviceOperationRepository;
  }

  @Override
  public Long handle(AddRatingCommand command) {

    ServiceOperation serviceOperation = serviceOperationRepository.findByRequestId(command.requestId())
        .orElseThrow(() -> new IllegalArgumentException("No ServiceOperation found for the given RequestId"));

    if (serviceOperation.getStatus() != ServiceStatus.COMPLETED) {
      throw new IllegalStateException("Cannot add rating: associated ServiceOperation is not completed.");
    }

    var rating = new Rating(
        command.requestId(),
        command.score(),
        command.comment(),
        command.raterId(),
        command.technicianId()
    );

    ratingRepository.save(rating);
    return rating.getId();
  }

  @Override
  public void handle(UpdateRatingCommand command) {
    var rating = ratingRepository.findById(command.ratingId())
        .orElseThrow(() -> new IllegalArgumentException("Rating not found"));
    rating.updateScore(command.score());
    rating.updateComment(command.comment());
    ratingRepository.save(rating);
  }

  @Override
  public void handle(DeleteRatingCommand command) {
    var rating = ratingRepository.findById(command.ratingId())
        .orElseThrow(() -> new IllegalArgumentException("Rating not found"));
    ratingRepository.delete(rating);
  }
}