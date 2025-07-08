package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReportPhotoResource(
    @NotNull Long reportId,
    @NotBlank String url
) {}
