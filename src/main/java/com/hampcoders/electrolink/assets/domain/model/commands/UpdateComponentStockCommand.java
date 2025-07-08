package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;

import java.util.UUID;

public record UpdateComponentStockCommand(Long technicianId, Long componentId, int newQuantity, Integer newAlertThreshold) { }