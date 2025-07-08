package com.hampcoders.electrolink.iam.interfaces.rest.transform;

import com.hampcoders.electrolink.iam.domain.model.commands.SignInCommand;
import com.hampcoders.electrolink.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}
