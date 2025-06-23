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
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/component-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComponentTypeController {

    private final ComponentTypeCommandService componentTypeCommandService;
    private final ComponentTypeQueryService componentTypeQueryService;

    public ComponentTypeController(ComponentTypeCommandService componentTypeCommandService, ComponentTypeQueryService componentTypeQueryService) {
        this.componentTypeCommandService = componentTypeCommandService;
        this.componentTypeQueryService = componentTypeQueryService;
    }

    /**
     * Endpoint para crear un nuevo tipo de componente.
     * Sigue el patrón POST-then-GET.
     * @param resource Los datos para crear el tipo de componente.
     * @return El recurso del tipo de componente recién creado con el estado 201 (Created).
     */
    @PostMapping
    public ResponseEntity<ComponentTypeResource> createComponentType(@RequestBody @Valid CreateComponentTypeResource resource) {
        // 1. Traducir el Resource (DTO de la API) a un Command (Orden para el Dominio)
        var createComponentTypeCommand = CreateComponentTypeCommandFromResourceAssembler.toCommandFromResource(resource);

        // 2. Enviar el comando al servicio para su ejecución y obtener el ID
        var componentTypeId = componentTypeCommandService.handle(createComponentTypeCommand);

        // 3. Si el ID es nulo, algo salió mal
        if (componentTypeId == null) {
            return ResponseEntity.badRequest().build();
        }

        // 4. Usar el nuevo ID para consultar la entidad completa
        var getComponentTypeByIdQuery = new GetComponentTypeByIdQuery(componentTypeId);
        var componentType = componentTypeQueryService.handle(getComponentTypeByIdQuery);

        // 5. Si no se encuentra, es un error inesperado
        if (componentType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // 6. Traducir la Entidad a un Resource para la respuesta
        var componentTypeResource = ComponentTypeResourceFromEntityAssembler.toResourceFromEntity(componentType.get());

        // 7. Devolver la respuesta con el estado 201 CREATED
        return new ResponseEntity<>(componentTypeResource, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener todos los tipos de componente.
     * @return Una lista de todos los recursos de tipo de componente con el estado 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<ComponentTypeResource>> getAllComponentTypes() {
        var getAllComponentTypesQuery = new GetAllComponentTypesQuery();
        var componentTypes = componentTypeQueryService.handle(getAllComponentTypesQuery);

        var componentTypeResources = componentTypes.stream()
                .map(ComponentTypeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new ResponseEntity<>(componentTypeResources, HttpStatus.OK);
    }
}