package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

public record CreateRatingResource(
    Long requestId,
    Integer score,
    String comment,
    String raterId,
    Long technicianId
) {}