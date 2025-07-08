package com.hampcoders.electrolink.assets.interfaces.acl;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoryByTechnicianIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryCommandService;
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryQueryService;
import com.hampcoders.electrolink.assets.interfaces.rest.resource.TechnicianInventoryResource;
import com.hampcoders.electrolink.assets.interfaces.rest.transform.TechnicianInventoryResourceFromEntityAssembler;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryContextFacade {

    private final TechnicianInventoryCommandService technicianInventoryCommandService;
    private final TechnicianInventoryQueryService technicianInventoryQueryService;

    public InventoryContextFacade(
            TechnicianInventoryCommandService technicianInventoryCommandService,
            TechnicianInventoryQueryService technicianInventoryQueryService) {
        this.technicianInventoryCommandService = technicianInventoryCommandService;
        this.technicianInventoryQueryService = technicianInventoryQueryService;
    }

    public UUID createInventoryForTechnician(Long technicianId) {
        var command = new CreateTechnicianInventoryCommand(new TechnicianId(technicianId));
        return technicianInventoryCommandService.handle(command);
    }

    public Optional<TechnicianInventoryResource> fetchInventoryByTechnicianId(Long technicianId) {
        var query = new GetInventoryByTechnicianIdQuery(new TechnicianId(technicianId));
        return technicianInventoryQueryService.handle(query)
                .map(TechnicianInventoryResourceFromEntityAssembler::toResourceFromEntity);
    }

    public boolean existsInventoryForTechnician(Long technicianId) {
        var query = new GetInventoryByTechnicianIdQuery(new TechnicianId(technicianId));
        return technicianInventoryQueryService.handle(query).isPresent();
    }
}
