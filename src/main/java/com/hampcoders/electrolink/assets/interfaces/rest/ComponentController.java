package com.hampcoders.electrolink.assets.interfaces.rest;

import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByNameQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.services.ComponentCommandService;
import com.hampcoders.electrolink.assets.domain.services.ComponentQueryService;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentLookupResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreateComponentResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.UpdateComponentResource;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.ComponentLookupResourceFromEntityAssembler;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.ComponentResourceFromEntityAssembler;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.CreateComponentCommandFromResourceAssembler;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.UpdateComponentCommandFromResourceAssembler;
import org.springframework.http.MediaType;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Component Management", description = "API for managing components")
@RestController
@RequestMapping(value = "/api/v1/components", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComponentController {

    private final ComponentCommandService componentCommandService;
    private final ComponentQueryService componentQueryService;

    public ComponentController(ComponentCommandService componentCommandService, ComponentQueryService componentQueryService) {
        this.componentCommandService = componentCommandService;
        this.componentQueryService = componentQueryService;
    }

    @Operation(summary = "Get a component by ID", description = "Retrieve a specific component using its unique identifier.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Component found"),
        @ApiResponse(responseCode = "404", description = "Component not found")
    })
    @GetMapping("/{componentId}")
    public ResponseEntity<ComponentResource> getComponentById(
            @Parameter(description = "UUID of the component to retrieve") @PathVariable UUID componentId) {
        var getComponentByIdQuery = new GetComponentByIdQuery(new ComponentId(componentId));
        var component = componentQueryService.handle(getComponentByIdQuery);

        return component.map(p -> new ResponseEntity<>(
                        ComponentResourceFromEntityAssembler.toResourceFromEntity(p), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all components", description = "Retrieve a list of all components.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of components retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<ComponentResource>> getAllComponents() {
        var getAllComponentsQuery = new GetAllComponentsQuery();
        var components = componentQueryService.handle(getAllComponentsQuery);
        var componentResources = components.stream()
                .map(ComponentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(componentResources, HttpStatus.OK);
    }

    @Operation(summary = "Create a new component", description = "Create a new component with the provided details.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Component created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ComponentResource> createComponent(
            @Parameter(description = "Details of the component to create") @RequestBody @Valid CreateComponentResource resource) {
        var createComponentCommand = CreateComponentCommandFromResourceAssembler.toCommandFromResource(resource);
        var componentId = componentCommandService.handle(createComponentCommand);

        if (componentId == null || componentId.componentId() == null) {
            return ResponseEntity.badRequest().build();
        }

        var getComponentByIdQuery = new GetComponentByIdQuery(componentId);
        var component = componentQueryService.handle(getComponentByIdQuery);

        if (component.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        var componentResource = ComponentResourceFromEntityAssembler.toResourceFromEntity(component.get());
        return new ResponseEntity<>(componentResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing component", description = "Update the details of an existing component.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Component updated successfully"),
        @ApiResponse(responseCode = "404", description = "Component not found")
    })
    @PutMapping("/{componentId}")
    public ResponseEntity<ComponentResource> updateComponent(
            @Parameter(description = "UUID of the component to update") @PathVariable UUID componentId,
            @Parameter(description = "Updated details of the component") @RequestBody @Valid UpdateComponentResource resource) {
        var updateComponentCommand = UpdateComponentCommandFromResourceAssembler.toCommandFromResource(componentId, resource);
        var updatedComponent = componentCommandService.handle(updateComponentCommand);

        return updatedComponent.map(component -> new ResponseEntity<>(
                        ComponentResourceFromEntityAssembler.toResourceFromEntity(component), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete a component", description = "Delete a specific component using its unique identifier.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Component deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Component not found")
    })
    @DeleteMapping("/{componentId}")
    public ResponseEntity<Void> deleteComponent(
            @Parameter(description = "UUID of the component to delete") @PathVariable UUID componentId) {
        var deleteComponentCommand = new DeleteComponentCommand(componentId);
        Boolean result = componentCommandService.handle(deleteComponentCommand);

        return result ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
