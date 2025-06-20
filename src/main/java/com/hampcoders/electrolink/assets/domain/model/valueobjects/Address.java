package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import java.lang.String;

public record Address(String Street,String Number, String City, String PostalCode, String Country, float latitude, float longitude ) {

    public Address {
        if (Street == null || Street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (Number == null || Number.isBlank()) {
            throw new IllegalArgumentException("Number cannot be null or empty");
        }
        if (City == null || City.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (PostalCode == null || PostalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code cannot be null or empty");
        }
        if (Country == null || Country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
    }

    public Address() {
        this("", "", "", "", "", 0.0f, 0.0f);
    }
}
