package com.hampcoders.electrolink.profiles.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryCommandService;
import com.hampcoders.electrolink.profiles.application.internal.outboundservices.ExternalAssetsService;
import com.hampcoders.electrolink.profiles.domain.model.aggregates.Profile;
import com.hampcoders.electrolink.profiles.domain.model.commands.CreateProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.commands.DeleteProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.commands.UpdateProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.entities.HomeOwner;
import com.hampcoders.electrolink.profiles.domain.model.entities.Technician;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.EmailAddress;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.PersonName;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.StreetAddress;
import com.hampcoders.electrolink.profiles.domain.services.ProfileCommandService;
import com.hampcoders.electrolink.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

  private final ProfileRepository profileRepository;
  private final ExternalAssetsService externalAssetsService;

  public ProfileCommandServiceImpl(ProfileRepository profileRepository,ExternalAssetsService externalAssetsService) {
    this.profileRepository = profileRepository;
    this.externalAssetsService = externalAssetsService;
  }

  @Override
  public Long handle(CreateProfileCommand command) {
    if (profileRepository.existsByEmail_Address(command.email())) {
      throw new IllegalArgumentException("A profile with email " + command.email() + " already exists.");
    }

    var personName = new PersonName(command.firstName(), command.lastName());
    var email = new EmailAddress(command.email());
    var address = new StreetAddress(command.street());
    var role = command.role();

    var profile = new Profile(personName, email, address, role);

    if (role == Role.HOMEOWNER) {
      profile.assignHomeOwner(new HomeOwner(command.additionalInfoOrCertification()));
    } else if (role == Role.TECHNICIAN) {
      profile.assignTechnician(new Technician(command.additionalInfoOrCertification()));
    }

    try {
      profileRepository.save(profile);

      if (role == Role.TECHNICIAN) {
        externalAssetsService.createInventoryForTechnician(profile.getId());
      }
      return profile.getId();
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while saving profile: " + e.getMessage());
    }
  }

  @Override
  public Optional<Profile> handle(UpdateProfileCommand command) {
    var profileId = command.profileId();

    if (!profileRepository.existsById(profileId)) {
      throw new IllegalArgumentException("Profile with id " + profileId + " does not exist.");
    }

    if (profileRepository.existsByEmail_AddressAndIdIsNot(command.email(), profileId)) {
      throw new IllegalArgumentException("Email " + command.email() + " is already used by another profile.");
    }

    var profileToUpdate = profileRepository.findById(profileId).get();

    var personName = new PersonName(command.firstName(), command.lastName());
    var email = new EmailAddress(command.email());
    var address = new StreetAddress(command.street());
    var role = command.role();

    profileToUpdate.updateInformation(personName, email, address, role);

    if (role == Role.HOMEOWNER) {
      profileToUpdate.assignHomeOwner(new HomeOwner(command.additionalInfoOrCertification()));
    } else if (role == Role.TECHNICIAN) {
      profileToUpdate.assignTechnician(new Technician(command.additionalInfoOrCertification()));
    }

    try {
      return Optional.of(profileRepository.save(profileToUpdate));
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while updating profile: " + e.getMessage());
    }
  }

  @Override
  public void handle(DeleteProfileCommand command) {
    if (!profileRepository.existsById(command.profileId())) {
      throw new IllegalArgumentException("Profile with id " + command.profileId() + " does not exist.");
    }

    try {
      profileRepository.deleteById(command.profileId());
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while deleting profile: " + e.getMessage());
    }
  }
}
