package com.hampcoders.electrolink.monitoring.domain.model.commands;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportType;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;

public record AddReportCommand(RequestId requestId, ReportType reportType, String description) {}