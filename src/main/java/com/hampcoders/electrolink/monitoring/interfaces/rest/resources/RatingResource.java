package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import java.util.UUID;

public record RatingResource(
        Long id,
        Long requestId,
    Integer score,
    String comment,
    String raterId,
        Long technicianId
) {}