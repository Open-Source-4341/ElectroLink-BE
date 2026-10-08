package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestsByClientIdQuery;

import java.util.List;
import java.util.Optional;

public interface RequestQueryService {
    Optional<Request> handle(FindRequestByIdQuery query);
    List<Request> handle(FindRequestsByClientIdQuery query);
}
