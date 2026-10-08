package com.hampcoders.electrolink.assets.interfaces.rest;

import com.hampcoders.electrolink.assets.domain.model.commands.DeletePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesByOwnerIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetPropertyByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.OwnerId;
import com.hampcoders.electrolink.assets.domain.services.PropertyCommandService;
import com.hampcoders.electrolink.assets.domain.services.PropertyQueryService;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreatePropertyResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.PropertyResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.UpdatePropertyResource;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.CreatePropertyCommandFromResourceAssembler;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.PropertyResourceFromEntityAssembler;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.UpdatePropertyCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Property Management", description = "Endpoints for managing properties")
@RestController
@RequestMapping(value = "/api/v1/properties", produces = MediaType.APPLICATION_JSON_VALUE)
public class PropertyController {
    private final PropertyCommandService propertyCommandService;
    private final PropertyQueryService propertyQueryService;

    public PropertyController(PropertyCommandService propertyCommandService, PropertyQueryService propertyQueryService) {
        this.propertyCommandService = propertyCommandService;
        this.propertyQueryService = propertyQueryService;
    }

    @Operation(summary = "Retrieve all properties", description = "Fetches a list of all properties.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of properties")
    })
    @GetMapping
    public ResponseEntity<List<PropertyResource>> getAllProperties() {
        var getAllPropertiesQuery = new GetAllPropertiesQuery();
        var properties = propertyQueryService.handle(getAllPropertiesQuery);
        var propertyResources = properties.stream()
                .map(PropertyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(propertyResources, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve properties by owner ID", description = "Fetches a list of properties associated with a specific owner.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of properties"),
        @ApiResponse(responseCode = "400", description = "Invalid owner ID format")
    })
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PropertyResource>> getAllPropertiesByOwnerId(
            @Parameter(description = "UUID of the owner", required = true) @PathVariable Long ownerId) {
        var getAllPropertiesByOwnerIdQuery = new GetAllPropertiesByOwnerIdQuery(new OwnerId(ownerId));
        var properties = propertyQueryService.handle(getAllPropertiesByOwnerIdQuery);

        var propertyResources = properties.stream()
                .map(PropertyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new ResponseEntity<>(propertyResources, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a property by ID", description = "Fetches details of a specific property using its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the property details"),
        @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResource> getPropertyById(
            @Parameter(description = "UUID of the property", required = true) @PathVariable UUID propertyId) {
        var getPropertyByIdQuery = new GetPropertyByIdQuery(propertyId);
        var property = propertyQueryService.handle(getPropertyByIdQuery);

        return property.map(p -> new ResponseEntity<>(
                        PropertyResourceFromEntityAssembler.toResourceFromEntity(p),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a new property", description = "Creates a new property with the provided details.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Successfully created the property"),
        @ApiResponse(responseCode = "400", description = "Invalid property details"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<PropertyResource> createProperty(
            @Parameter(description = "Details of the property to create", required = true) @RequestBody @Valid CreatePropertyResource resource) {
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

    @Operation(summary = "Update an existing property", description = "Updates the details of an existing property.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully updated the property"),
        @ApiResponse(responseCode = "404", description = "Property not found"),
        @ApiResponse(responseCode = "400", description = "Invalid property details")
    })
    @PutMapping("/{propertyId}")
    public ResponseEntity<PropertyResource> updateProperty(
            @Parameter(description = "UUID of the property to update", required = true) @PathVariable UUID propertyId,
            @Parameter(description = "Updated details of the property", required = true) @RequestBody @Valid UpdatePropertyResource resource) {

        var updatePropertyCommand = UpdatePropertyCommandFromResourceAssembler
                .toCommandFromResource(propertyId, resource);

        var updatedProperty = propertyCommandService.handle(updatePropertyCommand);

        return updatedProperty.map(property -> new ResponseEntity<>(
                        PropertyResourceFromEntityAssembler.toResourceFromEntity(property),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete a property", description = "Deletes a property using its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Successfully deleted the property"),
        @ApiResponse(responseCode = "404", description = "Property not found")
    })
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> deleteProperty(
            @Parameter(description = "UUID of the property to delete", required = true) @PathVariable UUID propertyId) {
        var deletePropertyCommand = new DeletePropertyCommand(propertyId);
        Boolean result = propertyCommandService.handle(deletePropertyCommand);

        return result ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
