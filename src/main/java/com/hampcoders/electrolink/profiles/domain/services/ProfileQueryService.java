package com.hampcoders.electrolink.profiles.domain.services;

import com.hampcoders.electrolink.profiles.domain.model.aggregates.Profile;
import com.hampcoders.electrolink.profiles.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
  List<Profile> handle(GetAllProfilesQuery query);
  Optional<Profile> handle(GetProfileByIdQuery query);
  Optional<Profile> handle(GetProfileByFullNameQuery query);
  Optional<Profile> handle(GetProfileByEmailQuery query);
  List<Profile> handle(GetProfilesByRoleQuery query);

  List<Profile> handle(GetProfileByAgeQuery query);
}
