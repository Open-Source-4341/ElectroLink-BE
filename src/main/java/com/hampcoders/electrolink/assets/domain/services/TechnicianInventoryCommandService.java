package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
//import com.hampcoders.electrolink.assets.domain.model.commands.AddStockToInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentStockCommand;
//import com.hampcoders.electrolink.assets.domain.model.commands.IncreaseStockCommand;
//import com.hampcoders.electrolink.assets.domain.model.commands.DecreaseStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
//import com.hampcoders.electrolink.assets.domain.model.commands.RemoveComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;

import java.util.Optional;

public interface TechnicianInventoryCommandService {
    TechnicianId handle(CreateTechnicianInventoryCommand command);
    //Optional<TechnicianInventory> handle(AddStockToInventoryCommand command);
    //Optional<TechnicianInventory> handle(IncreaseStockCommand command);
    //Optional<TechnicianInventory> handle(DecreaseStockCommand command);
    Optional<TechnicianInventory> handle(UpdateComponentStockCommand command);
    Boolean handle(DeleteComponentStockCommand command);
}