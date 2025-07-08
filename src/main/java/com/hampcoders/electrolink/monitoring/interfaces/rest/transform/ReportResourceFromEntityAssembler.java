package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ReportResource;

public class ReportResourceFromEntityAssembler {

  public static ReportResource toResourceFromEntity(Report entity) {
    return new ReportResource(
        entity.getId(),
        entity.getRequestId().getId(),
        entity.getDescription(),
        entity.getReportType().name()
    );
  }
}