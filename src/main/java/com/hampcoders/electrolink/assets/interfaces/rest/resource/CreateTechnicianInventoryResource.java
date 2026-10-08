package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTechnicianInventoryResource(
        @NotNull Long technicianId
) {}