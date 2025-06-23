package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "components")
@Getter
public class Component extends AuditableAbstractAggregateRoot<Component> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "component_uid", unique = true, nullable = false)
    private UUID componentUid;

    @Getter
    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Getter
    @Column(name="description", length = 255, nullable = true)
    private String description;

    @Getter
    @Column(name="is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "component_type_id")
    private Long componentTypeId;

    protected Component() {
        this.name = "";
        this.description = "";
        this.componentTypeId = null;
        this.isActive = false;
    }

    public Component(CreateComponentCommand command) {
        this();
        this.componentUid = UUID.randomUUID();
        this.name = command.name();
        this.description = command.description();
        this.componentTypeId = command.componentTypeId(); // Corregido
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
