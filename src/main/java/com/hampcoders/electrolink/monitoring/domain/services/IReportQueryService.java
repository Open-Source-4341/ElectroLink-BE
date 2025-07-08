package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllReportsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportsByRequestIdQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IReportQueryService {
  List<Report> handle(GetAllReportsQuery query);
  Optional<Report> handle(GetReportByIdQuery query);
  List<Report> handle(GetReportsByRequestIdQuery query);
}
