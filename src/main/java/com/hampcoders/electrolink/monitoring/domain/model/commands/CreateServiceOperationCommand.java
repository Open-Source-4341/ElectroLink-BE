package com.hampcoders.electrolink.monitoring.domain.model.commands;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;

import java.time.OffsetDateTime;

public record CreateServiceOperationCommand(RequestId requestId, TechnicianId technicianId, OffsetDateTime startedAt, OffsetDateTime completedAt, ServiceStatus currentStatus) {}