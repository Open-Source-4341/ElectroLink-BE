package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

public record CreateReportResource(
    Long requestId,
    String reportType,
    String description
) {}