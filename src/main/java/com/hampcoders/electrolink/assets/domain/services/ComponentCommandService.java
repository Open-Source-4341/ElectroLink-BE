package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;

import java.util.Optional;

public interface ComponentCommandService {
    ComponentId handle(CreateComponentCommand command);
    Optional<Component> handle(UpdateComponentCommand command);
    Boolean handle(DeleteComponentCommand command);
}