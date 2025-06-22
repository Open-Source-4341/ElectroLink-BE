package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;

public record UpdateComponentStockCommand(TechnicianId technicianId, ComponentId componentId, int newQuantity, Integer newAlertThreshold) { }