package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.AddReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportPhotoId;

public interface IReportCommandService {
  Long handle(AddReportCommand command);
  void handle(DeleteReportCommand command);
  ReportPhotoId handle(AddPhotoCommand command);
}