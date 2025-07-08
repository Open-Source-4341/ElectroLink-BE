package com.hampcoders.electrolink.monitoring.domain.model.commands;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RatingId;

public record DeleteRatingCommand(RatingId ratingId) {}