package com.hampcoders.electrolink.sdp.interfaces.rest.resources;

public record UpdateScheduleResource(
        String technicianId,
        String day,
        String startTime,
        String endTime
) {}
