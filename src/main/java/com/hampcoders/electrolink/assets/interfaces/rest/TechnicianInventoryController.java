package com.hampcoders.electrolink.assets.interfaces.rest;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoryByTechnicianIdQuery;
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryCommandService; // Necesitarás crear este servicio
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryQueryService;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.*;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/technician-inventories", produces = MediaType.APPLICATION_JSON_VALUE)
public class TechnicianInventoryController {

    private final TechnicianInventoryCommandService inventoryCommandService;
    private final TechnicianInventoryQueryService inventoryQueryService;

    public TechnicianInventoryController(TechnicianInventoryCommandService inventoryCommandService, TechnicianInventoryQueryService inventoryQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
    }

    @PostMapping
    public ResponseEntity<TechnicianInventoryResource> createTechnicianInventory(@RequestBody @Valid CreateTechnicianInventoryResource resource) {
        var command = CreateTechnicianInventoryCommandFromResourceAssembler.toCommandFromResource(resource);
        var inventoryId = inventoryCommandService.handle(command);

        if (inventoryId == null) {
            return ResponseEntity.badRequest().build();
        }

        var query = new GetInventoryByTechnicianIdQuery(new TechnicianId(resource.technicianId()));
        var inventory = inventoryQueryService.handle(query);

        if (inventory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        var inventoryResource = TechnicianInventoryResourceFromEntityAssembler.toResourceFromEntity(inventory.get());
        return new ResponseEntity<>(inventoryResource, HttpStatus.CREATED);
    }

    @GetMapping("/technician/{technicianId}")
    // CORREGIDO: technicianId ahora es UUID
    public ResponseEntity<TechnicianInventoryResource> getInventoryByTechnicianId(@PathVariable UUID technicianId) {
        var query = new GetInventoryByTechnicianIdQuery(new TechnicianId(technicianId));
        var inventory = inventoryQueryService.handle(query);

        return inventory.map(inv -> new ResponseEntity<>(
                        TechnicianInventoryResourceFromEntityAssembler.toResourceFromEntity(inv), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/technician/{technicianId}/stocks")
    public ResponseEntity<TechnicianInventoryResource> addComponentToStock(
            @PathVariable UUID technicianId,
            @RequestBody @Valid AddComponentStockResource resource) {

        var command = AddComponentStockCommandFromResourceAssembler.toCommandFromResource(technicianId, resource);
        var updatedInventory = inventoryCommandService.handle(command); // Asume que este método devuelve el inventario actualizado

        return updatedInventory.map(inv -> new ResponseEntity<>(
                        TechnicianInventoryResourceFromEntityAssembler.toResourceFromEntity(inv), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/technician/{technicianId}/stocks/{componentId}")
    // CORREGIDO: technicianId ahora es UUID
    public ResponseEntity<TechnicianInventoryResource> updateComponentStock(
            @PathVariable UUID technicianId,
            @PathVariable UUID componentId,
            @RequestBody @Valid UpdateComponentStockResource resource) {

        var command = UpdateComponentStockCommandFromResourceAssembler.toCommandFromResource(technicianId, componentId, resource);
        var updatedInventory = inventoryCommandService.handle(command);

        return updatedInventory.map(inv -> new ResponseEntity<>(
                        TechnicianInventoryResourceFromEntityAssembler.toResourceFromEntity(inv), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}