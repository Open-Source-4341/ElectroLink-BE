package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;

import java.util.Optional;

public interface ComponentTypeCommandService {
    ComponentTypeId handle(CreateComponentTypeCommand command);
    Optional<ComponentType> handle(UpdateComponentTypeCommand command);
    Boolean handle(DeleteComponentTypeCommand command);
}