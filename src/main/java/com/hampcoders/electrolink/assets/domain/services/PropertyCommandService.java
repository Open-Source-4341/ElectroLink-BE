package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.commands.CreatePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeletePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdatePropertyCommand;

import java.util.Optional;
import java.util.UUID;

public interface PropertyCommandService {
    UUID handle(CreatePropertyCommand command);
    Optional<Property> handle(UpdatePropertyCommand command);
    Boolean handle(DeletePropertyCommand command);
}