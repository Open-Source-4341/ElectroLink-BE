package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportPhotoId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportPhotoResource;

public class CreateReportPhotoCommandFromResourceAssembler {

  public static AddPhotoCommand toCommandFromResource(CreateReportPhotoResource resource) {
    return new AddPhotoCommand(
        new ReportPhotoId(), // genera el ID de la foto del reporte
        new ReportId(resource.reportId()),
        resource.url()
    );
  }
}
