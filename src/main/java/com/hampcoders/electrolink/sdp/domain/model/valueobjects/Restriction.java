package com.hampcoders.electrolink.sdp.domain.model.valueobjects;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor
public class Restriction {
    @ElementCollection
    private List<String> unavailableDistricts = new ArrayList<>();

    @ElementCollection
    private List<String> forbiddenDays = new ArrayList<>();

    private boolean requiresSpecialCertification;

    public Restriction(List<String> unavailableDistricts, List<String> forbiddenDays, boolean requiresSpecialCertification) {
        this.unavailableDistricts = unavailableDistricts;
        this.forbiddenDays = forbiddenDays;
        this.requiresSpecialCertification = requiresSpecialCertification;
    }
}
