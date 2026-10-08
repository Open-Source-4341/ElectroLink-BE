package com.hampcoders.electrolink.sdp.application.internal.commandservices;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.services.RequestCommandService;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.RequestRepository;
import com.hampcoders.electrolink.sdp.interfaces.rest.transform.RequestMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RequestCommandServiceImpl implements RequestCommandService {

    private final RequestRepository requestRepository;

    public RequestCommandServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    @Transactional
    public Request handle(CreateRequestCommand command) {
        return requestRepository.save(RequestMapper.toModel(command.resource()));
    }

    @Override
    @Transactional
    public Request handle(UpdateRequestCommand command) {
        return requestRepository.findById(command.requestId())
                .map(existing -> {
                    existing.updateFrom(command.resource());
                    return requestRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
    }

    @Override
    @Transactional
    public void handle(DeleteRequestCommand command) {
        var request = requestRepository.findById(command.requestId())
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        requestRepository.delete(request);
    }
}
