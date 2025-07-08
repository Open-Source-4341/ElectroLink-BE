package com.hampcoders.electrolink.assets.application.internal.outboundservices;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileService {

    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<TechnicianId> fetchTechnicianIdByEmail(String email) {
        var profileId = profilesContextFacade.fetchProfileIdByEmail(email);
        if (profileId.equals(0L)) return Optional.empty();
        return Optional.of(new TechnicianId(profileId));
    }
}