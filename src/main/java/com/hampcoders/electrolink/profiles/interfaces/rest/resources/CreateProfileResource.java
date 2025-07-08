package com.hampcoders.electrolink.profiles.interfaces.rest.resources;

import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;

public record CreateProfileResource(
  String firstName,
  String lastName,
  String email,
  String street,
  Role role,
  String additionalInfoOrCertification
) {}

