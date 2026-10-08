package com.hampcoders.electrolink.sdp.domain.model.commands;

public record UpdateScheduleCommand(
        Long scheduleId,
        String technicianId,
        String day,
        String startTime,
        String endTime
) {}

