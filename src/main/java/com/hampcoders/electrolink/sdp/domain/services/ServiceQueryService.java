package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindServiceByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.GetAllServicesQuery;

import java.util.List;
import java.util.Optional;

public interface ServiceQueryService {
    Optional<ServiceEntity> handle(FindServiceByIdQuery query);
    List<ServiceEntity> handle(GetAllServicesQuery query);
}
