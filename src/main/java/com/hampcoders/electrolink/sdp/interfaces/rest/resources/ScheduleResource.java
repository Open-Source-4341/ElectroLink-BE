package com.hampcoders.electrolink.sdp.interfaces.rest.resources;

public record ScheduleResource(
        Long id,
        String technicianId,
        String day,
        String startTime,
        String endTime
) {}
