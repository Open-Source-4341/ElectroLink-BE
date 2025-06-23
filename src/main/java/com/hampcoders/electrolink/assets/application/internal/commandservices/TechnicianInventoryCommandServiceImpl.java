package com.hampcoders.electrolink.assets.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.commands.AddComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryCommandService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.TechnicianInventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TechnicianInventoryCommandServiceImpl implements TechnicianInventoryCommandService {

    private final TechnicianInventoryRepository technicianInventoryRepository;
    private final ComponentRepository componentRepository;

    public TechnicianInventoryCommandServiceImpl(TechnicianInventoryRepository technicianInventoryRepository, ComponentRepository componentRepository) {
        this.technicianInventoryRepository = technicianInventoryRepository;
        this.componentRepository = componentRepository;
    }

    @Override
    public UUID handle(CreateTechnicianInventoryCommand command) {
        if (technicianInventoryRepository.existsByTechnicianId(command.technicianId())) {
            throw new IllegalStateException("Technician inventory already exists for this technician ID");
        }
        var inventory = new TechnicianInventory(command);
        technicianInventoryRepository.save(inventory);
        return inventory.getId();
    }

    @Override
    public Optional<TechnicianInventory> handle(AddComponentStockCommand command) {
        // CORREGIDO: Pasamos el UUID del comando directamente al método del repositorio.
        var inventory = technicianInventoryRepository.findByTechnicianId(command.technicianId())
                .orElseThrow(() -> new EntityNotFoundException("TechnicianInventory not found for technician ID: " + command.technicianId()));

        var component = componentRepository.findByComponentUid(command.componentId())
                .orElseThrow(() -> new EntityNotFoundException("Component not found with UID: " + command.componentId()));

        inventory.addToStock(component, command.quantity(), command.alertThreshold());
        var savedInventory = technicianInventoryRepository.save(inventory);
        return Optional.of(savedInventory);
    }

    @Override
    public Optional<TechnicianInventory> handle(UpdateComponentStockCommand command) {
        var inventory = technicianInventoryRepository.findByTechnicianIdWithStocks(command.technicianId())
                .orElseThrow(() -> new EntityNotFoundException("TechnicianInventory not found for technician ID: " + command.technicianId()));

        // El método updateStockItem en tu agregado ya usa un comando.
        var domainCommand = new UpdateComponentStockCommand(
                command.technicianId(),
                command.componentId(),
                command.newQuantity(),
                command.newAlertThreshold()
        );

        boolean updated = inventory.updateStockItem(domainCommand);
        if (!updated) {
            return Optional.empty(); // O lanzar una excepción si el item de stock no se encontró
        }
        var savedInventory = technicianInventoryRepository.save(inventory);
        return Optional.of(savedInventory);
    }
}