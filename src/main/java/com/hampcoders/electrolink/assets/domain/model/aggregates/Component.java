package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "components")
@Getter
public class Component extends AuditableAbstractAggregateRoot<Component> {

    @Embedded
    @AttributeOverrides( {@AttributeOverride(name = "componentId", column = @Column(name = "component_id", nullable = false))
    })
    private ComponentId componentId;

    @Getter
    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Getter
    @Column(name="description", length = 255, nullable = true)
    private String description;

    @Getter
    @Column(name="is_active", nullable = false)
    private Boolean isActive;

    @Embedded
    private ComponentTypeId componentTypeId;

    protected Component() {
        this.name = "";
        this.description = "";
        this.componentTypeId = new ComponentTypeId(0);
        this.isActive = false;
    }

    public Component(String name, String description, int componentTypeId) {
        this();
        this.componentId = ComponentId.newId();
        this.name = name;
        this.description = description;
        this.componentTypeId = new ComponentTypeId(componentTypeId);
        this.isActive = true;
    }
    public void updateInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void deactivate() {
        this.isActive = false;
    }
}
