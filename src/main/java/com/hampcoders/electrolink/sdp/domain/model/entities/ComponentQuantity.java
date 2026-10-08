package com.hampcoders.electrolink.sdp.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ComponentQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String componentId;
    private int quantity;

    public ComponentQuantity(String componentId, int quantity) {
        this.componentId = componentId;
        this.quantity = quantity;
    }
}
