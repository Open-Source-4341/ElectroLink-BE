package com.hampcoders.electrolink.sdp.domain.model.commands;

import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateRequestResource;

public record UpdateRequestCommand(Long requestId, CreateRequestResource resource) {
}
