package com.hampcoders.electrolink.sdp.domain.model.commands;

public record CreateScheduleCommand(
        String technicianId,
        String day,
        String startTime,
        String endTime
) {}
