package com.hampcoders.electrolink.profiles.interfaces.rest.transform;

import com.hampcoders.electrolink.profiles.domain.model.commands.CreateProfileCommand;
import com.hampcoders.electrolink.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {

  public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
    return new CreateProfileCommand(
      resource.firstName(),
      resource.lastName(),
      resource.email(),
      resource.street(),
      resource.role(),
      resource.additionalInfoOrCertification()
    );
  }
}
