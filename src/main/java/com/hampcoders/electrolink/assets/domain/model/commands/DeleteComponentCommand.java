package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;

public record DeleteComponentCommand(TechnicianId technicianId) {
}
