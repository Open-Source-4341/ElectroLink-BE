package com.hampcoders.electrolink.profiles.domain.model.commands;

import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;

public record UpdateProfileCommand(
  Long profileId,
  String firstName,
  String lastName,
  String email,
  String street,
  Role role,
  String additionalInfoOrCertification
) {}

