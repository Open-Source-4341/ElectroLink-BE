package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;

public record UpdateComponentTypeCommand(ComponentTypeId id, String name, String description) { }