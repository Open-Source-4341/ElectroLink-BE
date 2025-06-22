package com.hampcoders.electrolink.assets.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryCommandService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.TechnicianInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnicianInventoryCommandServiceImpl implements TechnicianInventoryCommandService {

    private final TechnicianInventoryRepository technicianInventoryRepository;

    public TechnicianInventoryCommandServiceImpl(TechnicianInventoryRepository technicianInventoryRepository) {
        this.technicianInventoryRepository = technicianInventoryRepository;
    }


    @Override
    public TechnicianId handle(CreateTechnicianInventoryCommand command) {
        if (technicianInventoryRepository.findByTechnicianId(command.technicianId()).isPresent()) {
            throw new IllegalStateException("Technician already has an inventory.");
        }

        var technicianInventory = new TechnicianInventory(command);

        technicianInventoryRepository.save(technicianInventory);

        return technicianInventory.getTechnicianId();
    }


    @Override
    public Optional<TechnicianInventory> handle(UpdateComponentStockCommand command) {
        return technicianInventoryRepository.findByTechnicianId(command.technicianId()).map(inventory -> {
            inventory.updateStockItem(command);

            return technicianInventoryRepository.save(inventory);
        });
    }

    @Override
    public Boolean handle(DeleteComponentStockCommand command) {
        return technicianInventoryRepository.findByTechnicianId(command.technicianId()).map(inventory -> {
            boolean removed = inventory.removeStockItem(command);

            technicianInventoryRepository.save(inventory);

            return removed;
        }).orElse(false);
    }
}