package com.hampcoders.electrolink.assets.interfaces.rest;

import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoriesWithLowStockQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetStockItemDetailsQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
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

import java.util.List;
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
    @GetMapping("/low-stock")
    public ResponseEntity<List<TechnicianInventoryResource>> getInventoriesWithLowStock() {
        var query = new GetInventoriesWithLowStockQuery(5);
        var inventories = inventoryQueryService.handle(query);

        var resources = inventories.stream()
                .map(TechnicianInventoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/technician/{technicianId}/stocks/{componentId}")
    public ResponseEntity<ComponentStockResource> getStockItemDetails(
            @PathVariable UUID technicianId,
            @PathVariable UUID componentId) {

        var query = new GetStockItemDetailsQuery(
                new TechnicianId(technicianId),
                new ComponentId(componentId)
        );
        var stockItem = inventoryQueryService.handle(query);

        return stockItem.map(item -> new ResponseEntity<>(
                        ComponentStockResourceFromEntityAssembler.toResourceFromEntity(item),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/technician/{technicianId}")
    public ResponseEntity<TechnicianInventoryResource> getInventoryByTechnicianId(@PathVariable UUID technicianId) {
        var query = new GetInventoryByTechnicianIdQuery(new TechnicianId(technicianId));
        var inventory = inventoryQueryService.handle(query);

        return inventory.map(inv -> new ResponseEntity<>(
                        TechnicianInventoryResourceFromEntityAssembler.toResourceFromEntity(inv), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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


    @PostMapping("/technician/{technicianId}/stocks")
    public ResponseEntity<TechnicianInventoryResource> addComponentToStock(
            @PathVariable UUID technicianId,
            @RequestBody @Valid AddComponentStockResource resource) {

        var command = AddComponentStockCommandFromResourceAssembler.toCommandFromResource(technicianId, resource);
        var updatedInventory = inventoryCommandService.handle(command);

        return updatedInventory.map(inv -> new ResponseEntity<>(
                        TechnicianInventoryResourceFromEntityAssembler.toResourceFromEntity(inv), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/technician/{technicianId}/stocks/{componentId}")
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

    @DeleteMapping("/technician/{technicianId}/stocks/{componentId}")
    public ResponseEntity<Void> deleteComponentFromInventory(
            @PathVariable UUID technicianId,
            @PathVariable UUID componentId) {

        var command = new DeleteComponentStockCommand(technicianId, componentId);
        boolean result = inventoryCommandService.handle(command);

        return result ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }


}