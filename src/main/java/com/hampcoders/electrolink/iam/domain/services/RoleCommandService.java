package com.hampcoders.electrolink.iam.domain.services;

import com.hampcoders.electrolink.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(com.hampcoders.electrolink.iam.domain.model.commands.SeedRolesCommand command);
}
