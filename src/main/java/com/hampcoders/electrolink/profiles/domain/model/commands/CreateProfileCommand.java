package com.hampcoders.electrolink.profiles.domain.model.commands;

import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;

public record CreateProfileCommand(
  String firstName,
  String lastName,
  String email,
  String street,
  Role role,
  String additionalInfoOrCertification
) {}

