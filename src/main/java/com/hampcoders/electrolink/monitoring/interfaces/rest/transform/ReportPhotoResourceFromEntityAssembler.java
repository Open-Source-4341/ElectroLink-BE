package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ReportPhotoResource;

public class ReportPhotoResourceFromEntityAssembler {
  public static ReportPhotoResource toResourceFromEntity(ReportPhoto entity) {
    return new ReportPhotoResource(
        entity.getId().getId(),
        entity.getReportId().getId(),
        entity.getUrl()
    );
  }
}