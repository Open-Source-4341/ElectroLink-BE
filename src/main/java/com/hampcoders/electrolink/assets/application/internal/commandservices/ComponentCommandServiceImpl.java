package com.hampcoders.electrolink.assets.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.services.ComponentCommandService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComponentCommandServiceImpl implements ComponentCommandService {

    private final ComponentRepository componentRepository;

    public ComponentCommandServiceImpl(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public ComponentId handle(CreateComponentCommand command) {
        if (componentRepository.existsByName(command.name())) {
            throw new IllegalStateException("Component with the same name already exists");
        }
        var component = new Component(command);
        componentRepository.save(component);
        return component.getComponentId();
    }

    @Override
    public Optional<Component> handle(UpdateComponentCommand command) {
        return componentRepository.findById(command.componentId()).map(component -> {
            component.updateInfo(command.name(), command.description());
            return componentRepository.save(component);
        });
    }

    @Override
    public Boolean handle(DeleteComponentCommand command) {
        if (!componentRepository.existsById(command.componentId())) {
            return false;
        }
        componentRepository.deleteById(command.componentId());
        return true;
    }
}