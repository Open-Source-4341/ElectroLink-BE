package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;

import java.util.List;

public record GetComponentsByIdsQuery(List<ComponentId> ids) {
}
