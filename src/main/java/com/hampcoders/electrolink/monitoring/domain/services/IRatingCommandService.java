package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateRatingCommand;

public interface IRatingCommandService {
  Long handle(AddRatingCommand command);
  void handle(UpdateRatingCommand command);
  void handle(DeleteRatingCommand command);
}