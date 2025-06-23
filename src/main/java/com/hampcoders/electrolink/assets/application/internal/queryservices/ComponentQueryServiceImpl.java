package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByIdsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByTypeIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.services.ComponentQueryService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComponentQueryServiceImpl implements ComponentQueryService {

    private final ComponentRepository componentRepository;

    public ComponentQueryServiceImpl(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public Optional<Component> handle(GetComponentByIdQuery query) {
        return componentRepository.findById(query.componentId().componentId());
    }

    @Override
    public List<Component> handle(GetAllComponentsQuery query) {
        return componentRepository.findAll();
    }

    @Override
    public List<Component> handle(GetComponentsByTypeIdQuery query) {
        return componentRepository.findByComponentTypeId(query.componentTypeId());
    }

    @Override
    public List<Component> handle(GetComponentsByIdsQuery query) {
        List<UUID> uuidList = query.ids().stream()
                .map(ComponentId::componentId)
                .toList();
        return componentRepository.findByComponentUidIn(uuidList);
    }
}