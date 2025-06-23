package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePropertyResource(
        @NotNull @Valid AddressResource address,
        @NotBlank String region,
        @NotBlank String district
) {
}