package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.queries.GetPropertyByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesByOwnerIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPhotosByPropertyIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.PropertyPhoto;

import java.util.List;
import java.util.Optional;

public interface PropertyQueryService {
    Optional<Property> handle(GetPropertyByIdQuery query);
    List<Property> handle(GetAllPropertiesByOwnerIdQuery query);
    List<Property> handle(GetAllPropertiesQuery query);
}