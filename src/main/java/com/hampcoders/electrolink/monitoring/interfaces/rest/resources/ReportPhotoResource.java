package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import java.util.UUID;

public record ReportPhotoResource(
        Long id,
        Long reportId,
        String url
) {}