package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.commands.AddComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
//import com.hampcoders.electrolink.assets.domain.model.commands.AddStockToInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentStockCommand;
//import com.hampcoders.electrolink.assets.domain.model.commands.IncreaseStockCommand;
//import com.hampcoders.electrolink.assets.domain.model.commands.DecreaseStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
//import com.hampcoders.electrolink.assets.domain.model.commands.RemoveComponentStockCommand;

import java.util.Optional;
import java.util.UUID;

public interface TechnicianInventoryCommandService {
    UUID handle(CreateTechnicianInventoryCommand command);
    Optional<TechnicianInventory> handle(AddComponentStockCommand command);
    Optional<TechnicianInventory> handle(UpdateComponentStockCommand command);
    Boolean handle(DeleteComponentStockCommand command);
}