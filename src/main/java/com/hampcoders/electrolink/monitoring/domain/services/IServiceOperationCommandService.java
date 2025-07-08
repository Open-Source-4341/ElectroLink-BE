package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.commands.CreateServiceOperationCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateServiceStatusCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;

import java.util.UUID;

public interface IServiceOperationCommandService {
  RequestId handle(CreateServiceOperationCommand command);
  void handle(UpdateServiceStatusCommand command);
}