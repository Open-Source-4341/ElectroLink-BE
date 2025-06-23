package com.hampcoders.electrolink.assets.interfaces.rest;

import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.services.ComponentCommandService;
import com.hampcoders.electrolink.assets.domain.services.ComponentQueryService;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.ComponentResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.CreateComponentResource;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.UpdateComponentResource;
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

@RestController
@RequestMapping(value = "/api/v1/components", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComponentController {

    private final ComponentCommandService componentCommandService;
    private final ComponentQueryService componentQueryService;

    public ComponentController(ComponentCommandService componentCommandService, ComponentQueryService componentQueryService) {
        this.componentCommandService = componentCommandService;
        this.componentQueryService = componentQueryService;
    }

    @PostMapping
    public ResponseEntity<ComponentResource> createComponent(@RequestBody @Valid CreateComponentResource resource) {
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

    @GetMapping("/{componentId}")
    public ResponseEntity<ComponentResource> getComponentById(@PathVariable UUID componentId) {
        var getComponentByIdQuery = new GetComponentByIdQuery(new ComponentId(componentId));
        var component = componentQueryService.handle(getComponentByIdQuery);

        return component.map(p -> new ResponseEntity<>(
                        ComponentResourceFromEntityAssembler.toResourceFromEntity(p), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ComponentResource>> getAllComponents() {
        var getAllComponentsQuery = new GetAllComponentsQuery();
        var components = componentQueryService.handle(getAllComponentsQuery);
        var componentResources = components.stream()
                .map(ComponentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(componentResources, HttpStatus.OK);
    }

    @PutMapping("/{componentId}")
    public ResponseEntity<ComponentResource> updateComponent(@PathVariable UUID componentId, @RequestBody @Valid UpdateComponentResource resource) {
        var updateComponentCommand = UpdateComponentCommandFromResourceAssembler.toCommandFromResource(componentId, resource);
        var updatedComponent = componentCommandService.handle(updateComponentCommand);

        return updatedComponent.map(component -> new ResponseEntity<>(
                        ComponentResourceFromEntityAssembler.toResourceFromEntity(component), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}