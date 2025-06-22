package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;

public record DeleteComponentTypeCommand(ComponentTypeId componentTypeId) {
}
