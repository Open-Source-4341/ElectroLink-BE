package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;

import java.util.UUID;

public record DeleteComponentCommand(UUID componentId) {
}
