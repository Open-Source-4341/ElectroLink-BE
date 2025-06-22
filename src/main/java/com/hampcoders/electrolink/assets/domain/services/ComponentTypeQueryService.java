package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentTypesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentTypeByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ComponentTypeQueryService {
    Optional<ComponentType> handle(GetComponentTypeByIdQuery query);
    List<ComponentType> handle(GetAllComponentTypesQuery query);
}