package com.hampcoders.electrolink.sdp.interfaces.rest;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindServiceByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.GetAllServicesQuery;
import com.hampcoders.electrolink.sdp.domain.services.ServiceCommandService;
import com.hampcoders.electrolink.sdp.domain.services.ServiceQueryService;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateServiceResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.transform.ServiceMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    private final ServiceCommandService commandService;
    private final ServiceQueryService queryService;
    private final ServiceQueryService serviceQueryService;

    public ServiceController(ServiceCommandService commandService, ServiceQueryService queryService, ServiceQueryService serviceQueryService) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.serviceQueryService = serviceQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CreateServiceResource>> getAllServices() {
        List<ServiceEntity> services = serviceQueryService.handle(new GetAllServicesQuery());
        List<CreateServiceResource> resources = services.stream()
                .map(ServiceMapper::toResource)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateServiceResource resource) {
        CreateServiceCommand command = ServiceMapper.toCreateCommand(resource);
        Long id = commandService.handle(command);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<?> update(@PathVariable Long serviceId, @RequestBody CreateServiceResource resource) {
        UpdateServiceCommand command = ServiceMapper.toUpdateCommand(serviceId, resource);
        commandService.handle(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<?> delete(@PathVariable Long serviceId) {
        DeleteServiceCommand command = new DeleteServiceCommand(serviceId);
        commandService.handle(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceEntity> getById(@PathVariable Long serviceId) {
        var query = new FindServiceByIdQuery(serviceId);
        Optional<ServiceEntity> result = queryService.handle(query);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
