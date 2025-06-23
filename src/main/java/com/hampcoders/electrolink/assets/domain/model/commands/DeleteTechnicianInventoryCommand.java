package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;

import java.util.UUID;

public record DeleteTechnicianInventoryCommand(UUID technicianId) {
}
