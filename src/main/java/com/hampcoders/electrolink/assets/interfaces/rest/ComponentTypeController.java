package com.hampcoders.electrolink.assets.interfaces.rest;

import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentTypesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentTypeByIdQuery;
import com.hampcoders.electrolink.assets.domain.services.ComponentTypeCommandService;
import com.hampcoders.electrolink.assets.domain.services.ComponentTypeQueryService;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentTypeResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreateComponentTypeResource;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.CreateComponentTypeCommandFromResourceAssembler;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentTypeResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreateComponentTypeResource;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.ComponentTypeResourceFromEntityAssembler;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.CreateComponentTypeCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Component Types", description = "API for managing component types")
@RestController
@RequestMapping(value = "/api/v1/component-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComponentTypeController {

    private final ComponentTypeCommandService componentTypeCommandService;
    private final ComponentTypeQueryService componentTypeQueryService;

    public ComponentTypeController(ComponentTypeCommandService componentTypeCommandService, ComponentTypeQueryService componentTypeQueryService) {
        this.componentTypeCommandService = componentTypeCommandService;
        this.componentTypeQueryService = componentTypeQueryService;
    }

    @Operation(summary = "Get all component types", description = "Retrieve a list of all available component types.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of component types",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComponentTypeResource.class)))
    })
    @GetMapping
    public ResponseEntity<List<ComponentTypeResource>> getAllComponentTypes() {
        var getAllComponentTypesQuery = new GetAllComponentTypesQuery();
        var componentTypes = componentTypeQueryService.handle(getAllComponentTypesQuery);

        var componentTypeResources = componentTypes.stream()
                .map(ComponentTypeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new ResponseEntity<>(componentTypeResources, HttpStatus.OK);
    }

    @Operation(summary = "Create a new component type", description = "Create a new component type and return its details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created the component type",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComponentTypeResource.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ComponentTypeResource> createComponentType(@RequestBody @Valid CreateComponentTypeResource resource) {
        var createComponentTypeCommand = CreateComponentTypeCommandFromResourceAssembler.toCommandFromResource(resource);

        var componentTypeId = componentTypeCommandService.handle(createComponentTypeCommand);

        if (componentTypeId == null) {
            return ResponseEntity.badRequest().build();
        }

        var getComponentTypeByIdQuery = new GetComponentTypeByIdQuery(componentTypeId);
        var componentType = componentTypeQueryService.handle(getComponentTypeByIdQuery);

        if (componentType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        var componentTypeResource = ComponentTypeResourceFromEntityAssembler.toResourceFromEntity(componentType.get());

        return new ResponseEntity<>(componentTypeResource, HttpStatus.CREATED);
    }


}
