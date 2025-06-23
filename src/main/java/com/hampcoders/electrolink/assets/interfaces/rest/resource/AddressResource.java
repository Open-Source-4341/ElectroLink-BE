package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

public record AddressResource(
        String street,String number, String city, String postalCode, String country, float latitude, float longitude
) {
}