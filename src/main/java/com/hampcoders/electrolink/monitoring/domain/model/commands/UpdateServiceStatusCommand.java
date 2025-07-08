package com.hampcoders.electrolink.monitoring.domain.model.commands;

public record UpdateServiceStatusCommand(Long requestId, String newStatus) {}
