package com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
  Optional<Report> findById(ReportId reportId);
  List<Report> findByRequestId(RequestId requestId);
}