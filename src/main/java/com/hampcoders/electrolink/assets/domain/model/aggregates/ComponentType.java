package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name="component_types")
@Getter
public class ComponentType extends AuditableAbstractAggregateRoot<ComponentType> {

    @Embedded
    private ComponentTypeId componentTypeId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    protected ComponentType() {

    }

    public ComponentType(CreateComponentTypeCommand command){
        this();
        this.componentTypeId = new ComponentTypeId();
        this.name = name;
        this.description = description;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void updateName(UpdateComponentTypeCommand command) {
        this.name = command.name();
    }

}
