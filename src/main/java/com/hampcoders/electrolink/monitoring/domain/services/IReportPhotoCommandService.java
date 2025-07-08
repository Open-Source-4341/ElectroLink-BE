package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;

public interface IReportPhotoCommandService {
  Long handle(AddPhotoCommand command);
}