package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllServiceOperationsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationsByTechnicianIdQuery;

import java.util.List;
import java.util.Optional;

public interface IServiceOperationQueryService {
  List<ServiceOperation> handle(GetAllServiceOperationsQuery query);
  Optional<ServiceOperation> handle(GetServiceOperationByIdQuery query);
  List<ServiceOperation> handle(GetServiceOperationsByTechnicianIdQuery query);
}