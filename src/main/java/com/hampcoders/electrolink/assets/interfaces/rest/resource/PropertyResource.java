package com.hampcoders.electrolink.assets.interfaces.rest.resource;

public record PropertyResource(
        String id,
        String ownerId,
        AddressResource address,
        RegionResource region,
        DistrictResource district
) {
}