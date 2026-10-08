package com.hampcoders.electrolink.profiles.interfaces.rest.transform;

import com.hampcoders.electrolink.profiles.domain.model.commands.UpdateProfileCommand;
import com.hampcoders.electrolink.profiles.interfaces.rest.resources.ProfileResource;

public class UpdateProfileCommandFromResourceAssembler {

  public static UpdateProfileCommand toCommandFromResource(Long profileId, ProfileResource resource) {
    return new UpdateProfileCommand(
      profileId,
      resource.firstName(),
      resource.lastName(),
      resource.email(),
      resource.street(),
      resource.role(),
      resource.additionalInfoOrCertification()
    );
  }
}
