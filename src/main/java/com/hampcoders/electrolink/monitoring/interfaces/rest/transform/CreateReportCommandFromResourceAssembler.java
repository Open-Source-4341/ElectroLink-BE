package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportType;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportResource;

public class CreateReportCommandFromResourceAssembler {

  public static AddReportCommand toCommandFromResource(CreateReportResource resource) {
    return new AddReportCommand(
        new RequestId(resource.requestId()),
        ReportType.valueOf(resource.reportType()),
        resource.description()
    );
  }
}