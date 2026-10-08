package com.hampcoders.electrolink.sdp.interfaces.rest.transform;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.entities.ComponentQuantity;
import com.hampcoders.electrolink.sdp.domain.model.entities.Tag;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Policy;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Restriction;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceMapper {

    public static ServiceEntity toModel(CreateServiceResource resource) {
        Policy policy = new Policy(resource.policy().cancellationPolicy(), resource.policy().termsAndConditions());
        Restriction restriction = new Restriction(
                resource.restriction().unavailableDistricts(),
                resource.restriction().forbiddenDays(),
                resource.restriction().requiresSpecialCertification()
        );
        List<Tag> tags = resource.tags().stream()
                .map(tag -> new Tag(tag.name()))
                .collect(Collectors.toList());

        List<ComponentQuantity> components = resource.components().stream()
                .map(comp -> new ComponentQuantity(comp.componentId(), comp.quantity()))
                .collect(Collectors.toList());

        return new ServiceEntity(
                resource.name(),
                resource.description(),
                resource.basePrice(),
                resource.estimatedTime(),
                resource.category(),
                resource.isVisible(),
                resource.createdBy(),
                policy,
                restriction,
                tags,
                components
        );
    }

    public static CreateServiceCommand toCreateCommand(CreateServiceResource resource) {
        Policy policy = new Policy(resource.policy().cancellationPolicy(), resource.policy().termsAndConditions());
        Restriction restriction = new Restriction(
                resource.restriction().unavailableDistricts(),
                resource.restriction().forbiddenDays(),
                resource.restriction().requiresSpecialCertification()
        );
        List<Tag> tags = resource.tags().stream()
                .map(tag -> new Tag(tag.name()))
                .collect(Collectors.toList());

        List<ComponentQuantity> components = resource.components().stream()
                .map(comp -> new ComponentQuantity(comp.componentId(), comp.quantity()))
                .collect(Collectors.toList());

        return new CreateServiceCommand(
                resource.name(),
                resource.description(),
                resource.basePrice(),
                resource.estimatedTime(),
                resource.category(),
                resource.isVisible(),
                resource.createdBy(),
                policy,
                restriction,
                tags,
                components
        );
    }

    public static UpdateServiceCommand toUpdateCommand(Long serviceId, CreateServiceResource resource) {
        Policy policy = new Policy(resource.policy().cancellationPolicy(), resource.policy().termsAndConditions());
        Restriction restriction = new Restriction(
                resource.restriction().unavailableDistricts(),
                resource.restriction().forbiddenDays(),
                resource.restriction().requiresSpecialCertification()
        );
        List<Tag> tags = resource.tags().stream()
                .map(tag -> new Tag(tag.name()))
                .collect(Collectors.toList());

        List<ComponentQuantity> components = resource.components().stream()
                .map(comp -> new ComponentQuantity(comp.componentId(), comp.quantity()))
                .collect(Collectors.toList());

        return new UpdateServiceCommand(
                serviceId,
                resource.name(),
                resource.description(),
                resource.basePrice(),
                resource.estimatedTime(),
                resource.category(),
                resource.isVisible(),
                resource.createdBy(),
                policy,
                restriction,
                tags,
                components
        );
    }

    public static CreateServiceResource toResource(ServiceEntity service) {
        var policy = service.getPolicy();
        var policyResource = policy != null
                ? new PolicyResource(policy.getCancellationPolicy(), policy.getTermsAndConditions())
                : new PolicyResource("", "");

        var restriction = service.getRestriction();
        var restrictionResource = restriction != null
                ? new RestrictionResource(
                restriction.getUnavailableDistricts(),
                restriction.getForbiddenDays(),
                restriction.isRequiresSpecialCertification()
        )
                : new RestrictionResource(List.of(), List.of(), false);

        var tagResources = service.getTags() != null
                ? service.getTags().stream().map(tag -> new TagResource(tag.getName())).toList()
                : Collections.emptyList();

        var componentResources = service.getComponents() != null
                ? service.getComponents().stream().map(c -> new ComponentQuantityResource(c.getComponentId(), c.getQuantity())).toList()
                : Collections.emptyList();

        return new CreateServiceResource(
                service.getName(),
                service.getDescription(),
                service.getBasePrice(),
                service.getEstimatedTime(),
                service.getCategory(),
                service.isVisible(),
                service.getCreatedBy(),
                policyResource,
                restrictionResource,
                (List<TagResource>) tagResources,
                (List<ComponentQuantityResource>) componentResources
        );
    }

}
