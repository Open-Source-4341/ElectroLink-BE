package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByIdsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByTypeIdQuery;

import java.util.List;
import java.util.Optional;

public interface ComponentQueryService {
    Optional<Component> handle(GetComponentByIdQuery query);
    List<Component> handle(GetAllComponentsQuery query);
    List<Component> handle(GetComponentsByTypeIdQuery query);
    List<Component> handle(GetComponentsByIdsQuery query);
}