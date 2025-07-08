package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;

import java.util.UUID;

public record UpdateComponentCommand(Long componentId, String name, String description, Long componentTypeId, Boolean isActive) {
}
