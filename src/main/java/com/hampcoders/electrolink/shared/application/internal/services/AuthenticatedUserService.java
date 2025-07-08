package com.hampcoders.electrolink.shared.application.internal.services;

import com.hampcoders.electrolink.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticatedUserService {

    public Optional<String> getAuthenticatedEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return Optional.empty();

        var principal = auth.getPrincipal();

        if (principal instanceof UserDetailsImpl userDetails) {
            return Optional.of(userDetails.getUsername());
        }

        return Optional.empty();
    }
}