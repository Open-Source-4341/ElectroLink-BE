package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPhotosByPropertyIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesByOwnerIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetPropertyByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.PropertyPhoto;
import com.hampcoders.electrolink.assets.domain.services.PropertyQueryService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyQueryServiceImpl implements PropertyQueryService {

    private final PropertyRepository propertyRepository;

    public PropertyQueryServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Optional<Property> handle(GetPropertyByIdQuery query) {
        return propertyRepository.findByIdWithPhoto(query.propertyId());
    }

    @Override
    public List<Property> handle(GetAllPropertiesByOwnerIdQuery query) {
        return propertyRepository.findByOwnerId(query.ownerId());
    }

    @Override
    public List<Property> handle(GetAllPropertiesQuery query) {
        return propertyRepository.findAll();
    }

    @Override
    public List<PropertyPhoto> handle(GetAllPhotosByPropertyIdQuery query) {
        var property = propertyRepository.findByIdWithPhoto(query.propertyId());
        if (property.isPresent() && property.get().getPhoto() != null) {
            return List.of(property.get().getPhoto());
        }
        return Collections.emptyList();
    }
}