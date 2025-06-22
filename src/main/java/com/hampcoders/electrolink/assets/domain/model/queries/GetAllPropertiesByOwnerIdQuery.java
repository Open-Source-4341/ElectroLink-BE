package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.OwnerId;

public record GetAllPropertiesByOwnerIdQuery(OwnerId ownerId) {
}
