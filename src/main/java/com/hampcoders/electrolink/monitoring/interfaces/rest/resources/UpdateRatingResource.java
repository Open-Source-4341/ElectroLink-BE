package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import java.util.UUID;

public record UpdateRatingResource(
    Long ratingId,
    Integer score,
    String comment
) {}