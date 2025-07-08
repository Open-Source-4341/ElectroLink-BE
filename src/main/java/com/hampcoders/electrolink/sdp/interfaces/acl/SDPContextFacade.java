package com.hampcoders.electrolink.sdp.interfaces.acl;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestsByClientIdQuery;
import com.hampcoders.electrolink.sdp.domain.services.RequestCommandService;
import com.hampcoders.electrolink.sdp.domain.services.RequestQueryService;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateRequestResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SDPContextFacade {

    private final RequestCommandService requestCommandService;
    private final RequestQueryService requestQueryService;

    public SDPContextFacade(RequestCommandService requestCommandService, RequestQueryService requestQueryService) {
        this.requestCommandService = requestCommandService;
        this.requestQueryService = requestQueryService;
    }

    public Optional<Request> fetchRequestById(Long requestId) {
        var query = new FindRequestByIdQuery(requestId);
        return requestQueryService.handle(query);
    }

    public List<Request> fetchRequestsByClientId(String clientId) {
        var query = new FindRequestsByClientIdQuery(clientId);
        return requestQueryService.handle(query);
    }

    public Long createRequest(CreateRequestResource resource) {
        var command = new CreateRequestCommand(resource);
        return requestCommandService.handle(command).getId();
    }

    public Long updateRequest(Long requestId, CreateRequestResource resource) {
        var command = new UpdateRequestCommand(requestId, resource);
        return requestCommandService.handle(command).getId();
    }

    public void deleteRequest(Long requestId) {
        var command = new DeleteRequestCommand(requestId);
        requestCommandService.handle(command);
    }
}
