package com.hampcoders.electrolink.sdp.domain.model.commands;

import com.hampcoders.electrolink.sdp.domain.model.entities.ComponentQuantity;
import com.hampcoders.electrolink.sdp.domain.model.entities.Tag;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Policy;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Restriction;

import java.util.List;

public record CreateServiceCommand(
        String name,
        String description,
        Double price,
        String estimatedTime,
        String category,
        boolean isVisible,
        String createdBy,
        Policy policy,
        Restriction restriction,
        List<Tag> tags,
        List<ComponentQuantity> components
) {}
