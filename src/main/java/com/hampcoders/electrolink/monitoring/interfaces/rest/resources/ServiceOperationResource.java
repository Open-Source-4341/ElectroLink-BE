package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import java.time.OffsetDateTime;
import java.util.UUID;

public record   ServiceOperationResource(
    Long requestId,
    Long technicianId,
    OffsetDateTime startedAt,
    OffsetDateTime completedAt,
    String currentStatus
) {}
