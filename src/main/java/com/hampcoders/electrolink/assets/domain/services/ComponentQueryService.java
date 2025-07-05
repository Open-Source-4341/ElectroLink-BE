package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ComponentQueryService {
    Optional<Component> handle(GetComponentByIdQuery query);
    List<Component> handle(GetAllComponentsQuery query);
    List<Component> handle(GetComponentsByTypeIdQuery query);
    List<Component> handle(GetComponentsByIdsQuery query);
    List<Component> handle(GetComponentsByNameQuery query);
}