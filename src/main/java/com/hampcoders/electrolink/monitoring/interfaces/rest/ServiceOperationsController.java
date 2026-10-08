package com.hampcoders.electrolink.monitoring.interfaces.rest;

import com.hampcoders.electrolink.monitoring.application.internal.commandservices.ServiceOperationCommandServiceImpl;
import com.hampcoders.electrolink.monitoring.application.internal.queryservices.ServiceOperationQueryServiceImpl;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateServiceStatusCommand;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllServiceOperationsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationsByTechnicianIdQuery;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateServiceOperationResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ServiceOperationResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateServiceStatusResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.CreateServiceOperationCommandFromResourceAssembler;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.ServiceOperationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/service-operations")
public class ServiceOperationsController {

  private final ServiceOperationCommandServiceImpl commandService;
  private final ServiceOperationQueryServiceImpl queryService;

  public ServiceOperationsController(ServiceOperationCommandServiceImpl commandService, ServiceOperationQueryServiceImpl queryService) {
    this.commandService = commandService;
    this.queryService = queryService;
  }

  @Operation(summary = "Create a new service operation")
  @PostMapping
  public ResponseEntity<Long> createServiceOperation(@Valid @RequestBody CreateServiceOperationResource resource) {
    var command = CreateServiceOperationCommandFromResourceAssembler.toCommandFromResource(resource);
    var id = commandService.handle(command);
    return new ResponseEntity<>(id.getId(), HttpStatus.CREATED);
  }

  @Operation(summary = "Get a service operation by ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Found successfully"),
      @ApiResponse(responseCode = "404", description = "Not found")
  })
  @GetMapping("/{serviceOperationId}")
  public ResponseEntity<ServiceOperationResource> getById(
      @Parameter(description = "Id of the service operation") @PathVariable Long serviceOperationId) {

    var result = queryService.handle(new GetServiceOperationByIdQuery(serviceOperationId))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service operation not found"));

    return ResponseEntity.ok(ServiceOperationResourceFromEntityAssembler.toResourceFromEntity(result));
  }

  @Operation(summary = "Get service operations by technician ID")
  @GetMapping("/technicians/{technicianId}")
  public ResponseEntity<List<ServiceOperationResource>> getServiceOperationsByTechnicianId(
      @Parameter(description = "Id of the technician") @PathVariable Long technicianId
  ) {
    var query = new GetServiceOperationsByTechnicianIdQuery(technicianId);
    var results = queryService.handle(query);
    var resources = results.stream()
        .map(ServiceOperationResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(resources);
  }
  @Operation(summary = "Get all service operations")
  @GetMapping
  public ResponseEntity<List<ServiceOperationResource>> getAll() {
    var results = queryService.handle(new GetAllServiceOperationsQuery());
    var resources = results.stream()
        .map(ServiceOperationResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(resources);
  }

  @Operation(summary = "Update service operation status")
  @PutMapping("/status")
  public ResponseEntity<Void> updateStatus(
      @Valid @RequestBody UpdateServiceStatusResource resource) {

    var command = new UpdateServiceStatusCommand(resource.requestId(), resource.newStatus());
    commandService.handle(command);
    return ResponseEntity.noContent().build();
  }
}