package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import java.util.UUID;

public record UpdateServiceStatusResource(
        Long requestId,
    String newStatus
) {}
