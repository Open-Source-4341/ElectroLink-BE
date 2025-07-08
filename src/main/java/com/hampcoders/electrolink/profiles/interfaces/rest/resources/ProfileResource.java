package com.hampcoders.electrolink.profiles.interfaces.rest.resources;

import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;

public record ProfileResource(
  Long id,
  String firstName,
  String lastName,
  String email,
  String street,
  Role role,
  String additionalInfoOrCertification
) {}


