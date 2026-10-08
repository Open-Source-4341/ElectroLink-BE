package com.hampcoders.electrolink.monitoring.application.internal.queryservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllReportsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportsByRequestIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.services.IReportQueryService;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportQueryServiceImpl implements IReportQueryService {

  private final ReportRepository reportRepository;

  public ReportQueryServiceImpl(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
  }

  @Override
  public List<Report> handle(GetAllReportsQuery query) {
    return reportRepository.findAll();
  }

  @Override
  public Optional<Report> handle(GetReportByIdQuery query) {
    return reportRepository.findById(new ReportId(query.reportId()));
  }

  @Override
  public List<Report> handle(GetReportsByRequestIdQuery query) {
    return reportRepository.findByRequestId(new RequestId(query.requestId()));
  }
}