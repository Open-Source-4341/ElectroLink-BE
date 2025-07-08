package com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RatingId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
  Optional<Rating> findById(RatingId ratingId);
  List<Rating> findByTechnicianId(TechnicianId technicianId);
  List<Rating> findByRequestId(RequestId requestId);
}
