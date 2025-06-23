package com.hampcoders.electrolink.assets.interfaces.rest;


import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetPropertyByIdQuery;
import com.hampcoders.electrolink.assets.domain.services.PropertyCommandService;
import com.hampcoders.electrolink.assets.domain.services.PropertyQueryService;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreatePropertyResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.PropertyResource;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.CreatePropertyCommandFromResourceAssembler;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.PropertyResourceFromEntityAssembler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/properties", produces = MediaType.APPLICATION_JSON_VALUE)
public class PropertyController {
    private final PropertyCommandService propertyCommandService;
    private final PropertyQueryService propertyQueryService;

    public PropertyController(PropertyCommandService propertyCommandService, PropertyQueryService propertyQueryService) {
        this.propertyCommandService = propertyCommandService;
        this.propertyQueryService = propertyQueryService;
    }

    @PostMapping
    public ResponseEntity<PropertyResource> createProperty(@RequestBody @Valid
                                                               CreatePropertyResource resource) {
        var createPropertyCommand = CreatePropertyCommandFromResourceAssembler.toCommandFromResource(resource);
        var propertyId = propertyCommandService.handle(createPropertyCommand);

        if (propertyId == null) {
            return ResponseEntity.badRequest().build();
        }

        var getPropertyByIdQuery = new GetPropertyByIdQuery(propertyId);
        var property = propertyQueryService.handle(getPropertyByIdQuery);

        if (property.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        var propertyResource = PropertyResourceFromEntityAssembler.toResourceFromEntity(property.get());
        return new ResponseEntity<>(propertyResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PropertyResource>> getAllProperties() {
        var getAllPropertiesQuery = new GetAllPropertiesQuery();
        var properties = propertyQueryService.handle(getAllPropertiesQuery);
        var propertyResources = properties.stream()
                .map(PropertyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(propertyResources, HttpStatus.OK);
    }


    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResource> getPropertyById(@PathVariable UUID propertyId) {
        var getPropertyByIdQuery = new GetPropertyByIdQuery(propertyId);
        var property = propertyQueryService.handle(getPropertyByIdQuery);

        return property.map(p -> new ResponseEntity<>(
                        PropertyResourceFromEntityAssembler.toResourceFromEntity(p),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
