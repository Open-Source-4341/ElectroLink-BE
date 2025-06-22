package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;

public record UpdateComponentCommand(ComponentId componentId, String name, String description, int componentTypeId, Boolean isActive) {
}
