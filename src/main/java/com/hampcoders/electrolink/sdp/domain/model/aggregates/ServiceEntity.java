package com.hampcoders.electrolink.sdp.domain.model.aggregates;

import com.hampcoders.electrolink.sdp.domain.model.entities.ComponentQuantity;
import com.hampcoders.electrolink.sdp.domain.model.entities.Tag;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Policy;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Restriction;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ServiceEntity extends AuditableAbstractAggregateRoot<ServiceEntity> {

    private String name;
    private String description;
    private Double basePrice;
    private String estimatedTime;
    private String category;
    private boolean isVisible;
    private String createdBy;

    @Embedded
    private Policy policy;

    @Embedded
    private Restriction restriction;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "service_id")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "service_id")
    private List<ComponentQuantity> components = new ArrayList<>();

    public ServiceEntity(String name, String description, Double basePrice, String estimatedTime, String category,
                         boolean isVisible, String createdBy, Policy policy, Restriction restriction,
                         List<Tag> tags, List<ComponentQuantity> components) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.estimatedTime = estimatedTime;
        this.category = category;
        this.isVisible = isVisible;
        this.createdBy = createdBy;
        this.policy = policy;
        this.restriction = restriction;
        this.tags = tags != null ? tags : new ArrayList<>();
        this.components = components != null ? components : new ArrayList<>();
    }

    // Método de actualización
    public void updateFrom(ServiceEntity updated) {
        this.name = updated.name;
        this.description = updated.description;
        this.basePrice = updated.basePrice;
        this.estimatedTime = updated.estimatedTime;
        this.category = updated.category;
        this.isVisible = updated.isVisible;
        this.policy = updated.policy;
        this.restriction = updated.restriction;
        this.tags.clear();
        this.tags.addAll(updated.tags);
        this.components.clear();
        this.components.addAll(updated.components);
    }

}
