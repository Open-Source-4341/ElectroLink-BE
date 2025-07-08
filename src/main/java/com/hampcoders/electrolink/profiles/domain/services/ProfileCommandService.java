package com.hampcoders.electrolink.profiles.domain.services;

import com.hampcoders.electrolink.profiles.domain.model.aggregates.Profile;
import com.hampcoders.electrolink.profiles.domain.model.commands.CreateProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.commands.DeleteProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
  Long handle(CreateProfileCommand command);
  Optional<Profile> handle(UpdateProfileCommand command);
  void handle(DeleteProfileCommand command);
}
