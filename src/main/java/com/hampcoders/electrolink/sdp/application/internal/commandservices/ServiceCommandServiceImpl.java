package com.hampcoders.electrolink.sdp.application.internal.commandservices;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.services.ServiceCommandService;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ServiceCommandServiceImpl implements ServiceCommandService {

    private final ServiceRepository serviceRepository;

    public ServiceCommandServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateServiceCommand command) {
        var service = new ServiceEntity(
                command.name(),
                command.description(),
                command.price(),
                command.estimatedTime(),
                command.category(),
                command.isVisible(),
                command.createdBy(),
                command.policy(),
                command.restriction(),
                command.tags(),
                command.components()
        );
        return serviceRepository.save(service).getId();
    }

    @Override
    @Transactional
    public void handle(UpdateServiceCommand command) {
        var existing = serviceRepository.findById(command.serviceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found with id: " + command.serviceId()));

        var updated = new ServiceEntity(
                command.name(),
                command.description(),
                command.price(),
                command.estimatedTime(),
                command.category(),
                command.isVisible(),
                command.createdBy(),
                command.policy(),
                command.restriction(),
                command.tags(),
                command.components()
        );

        existing.updateFrom(updated);
        serviceRepository.save(existing);
    }


    @Override
    @Transactional
    public void handle(DeleteServiceCommand command) {
        if (!serviceRepository.existsById(command.serviceId())) {
            throw new IllegalArgumentException("Service not found with id: " + command.serviceId());
        }
        serviceRepository.deleteById(command.serviceId());
    }
}
