package com.hampcoders.electrolink.assets.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import com.hampcoders.electrolink.assets.domain.services.ComponentTypeCommandService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComponentTypeCommandServiceImpl implements ComponentTypeCommandService {

    private final ComponentTypeRepository componentTypeRepository;
    private final ComponentRepository componentRepository;

    public ComponentTypeCommandServiceImpl(ComponentTypeRepository componentTypeRepository, ComponentRepository componentRepository) {
        this.componentTypeRepository = componentTypeRepository;
        this.componentRepository = componentRepository;
    }

    @Override
    public ComponentTypeId handle(CreateComponentTypeCommand command) {
        if (componentTypeRepository.existsByName(command.name())) {
            throw new IllegalStateException("Component type with the same name already exists");
        }
        var componentType = new ComponentType(command);
        componentTypeRepository.save(componentType);
        return componentType.getComponentTypeId();
    }

    @Override
    public Optional<ComponentType> handle(UpdateComponentTypeCommand command) {
        return componentTypeRepository.findById(command.id()).map(componentType -> {
            componentType.updateName(command);
            return componentTypeRepository.save(componentType);
        });
    }

    @Override
    public Boolean handle(DeleteComponentTypeCommand command) {
        if (!componentTypeRepository.existsById(command.componentTypeId())) {
            return false;
        }

        if (componentRepository.existsByComponentTypeId(command.componentTypeId())) {
            throw new IllegalStateException("Cannot delete component type: it is currently in use by one or more components.");
        }

        componentTypeRepository.deleteById(command.componentTypeId());
        return true;
    }
}