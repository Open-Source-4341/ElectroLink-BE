package com.hampcoders.electrolink.profiles.interfaces.rest.transform;

import com.hampcoders.electrolink.profiles.domain.model.aggregates.Profile;
import com.hampcoders.electrolink.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {

  public static ProfileResource toResourceFromEntity(Profile entity) {
    String info = null;
    switch (entity.getRole()) {
      case HOMEOWNER -> {
        if (entity.getHomeOwner() != null)
          info = entity.getHomeOwner().getAdditionalInfo();
      }
      case TECHNICIAN -> {
        if (entity.getTechnician() != null)
          info = entity.getTechnician().getCertificationCode();
      }
    }

    return new ProfileResource(
      entity.getId(),
      entity.getPersonName().firstName(),
      entity.getPersonName().lastName(),
      entity.getEmail().address(),
      entity.getAddress().street(),
      entity.getRole(),
      info
    );
  }
}
