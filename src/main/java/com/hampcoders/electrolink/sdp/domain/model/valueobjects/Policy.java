package com.hampcoders.electrolink.sdp.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Policy {
    private String cancellationPolicy;
    private String termsAndConditions;

    public Policy(String cancellationPolicy, String termsAndConditions) {
        this.cancellationPolicy = cancellationPolicy;
        this.termsAndConditions = termsAndConditions;
    }
}
