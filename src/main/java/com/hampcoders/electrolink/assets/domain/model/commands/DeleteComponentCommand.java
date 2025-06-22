package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;

public record DeleteComponentCommand(ComponentId componentId) {
}
