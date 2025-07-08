package com.hampcoders.electrolink.monitoring.domain.model.commands;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;

public record AddRatingCommand(
        RequestId requestId,
        int score,
        String comment,
        String raterId,
        TechnicianId technicianId
) {}
