package com.hampcoders.electrolink.sdp.interfaces.rest.resources;

public record CreateScheduleResource(
        String technicianId,
        String day,
        String startTime,
        String endTime
) {}
