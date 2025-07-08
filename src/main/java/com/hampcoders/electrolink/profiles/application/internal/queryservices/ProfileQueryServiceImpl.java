package com.hampcoders.electrolink.profiles.application.internal.queryservices;

import com.hampcoders.electrolink.profiles.domain.model.aggregates.Profile;
import com.hampcoders.electrolink.profiles.domain.model.queries.*;
import com.hampcoders.electrolink.profiles.domain.services.ProfileQueryService;
import com.hampcoders.electrolink.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

  private final ProfileRepository profileRepository;

  public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @Override
  public List<Profile> handle(GetAllProfilesQuery query) {
    return profileRepository.findAll();
  }

  @Override
  public Optional<Profile> handle(GetProfileByIdQuery query) {
    return profileRepository.findById(query.profileId());
  }

  @Override
  public Optional<Profile> handle(GetProfileByFullNameQuery query) {
    return profileRepository.findByPersonName_FirstNameAndPersonName_LastName(
      query.firstName(), query.lastName()
    );
  }

  @Override
  public Optional<Profile> handle(GetProfileByEmailQuery query) {
    return profileRepository.findByEmail_Address(query.email());
  }

  @Override
  public List<Profile> handle(GetProfilesByRoleQuery query) {
    return profileRepository.findByRole(query.role());
  }

  // Solo si decides mantener esta query
  @Override
  public List<Profile> handle(GetProfileByAgeQuery query) {
    throw new UnsupportedOperationException("Query by age is not supported in current model.");
  }
}
