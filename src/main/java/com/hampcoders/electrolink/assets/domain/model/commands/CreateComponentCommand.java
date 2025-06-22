package com.hampcoders.electrolink.assets.domain.model.commands;

public record CreateComponentCommand(String name, String description, int ComponentTypeId) {
}
