package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentTypesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentTypeByIdQuery;
import com.hampcoders.electrolink.assets.domain.services.ComponentTypeQueryService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponentTypeQueryServiceImpl implements ComponentTypeQueryService {

    private final ComponentTypeRepository componentTypeRepository;

    public ComponentTypeQueryServiceImpl(ComponentTypeRepository componentTypeRepository) {
        this.componentTypeRepository = componentTypeRepository;
    }

    @Override
    public Optional<ComponentType> handle(GetComponentTypeByIdQuery query) {
        return componentTypeRepository.findById(query.componentTypeId().id());
    }

    @Override
    public List<ComponentType> handle(GetAllComponentTypesQuery query) {
        return componentTypeRepository.findAll();
    }
}