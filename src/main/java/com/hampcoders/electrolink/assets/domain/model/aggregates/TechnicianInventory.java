package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
public class TechnicianInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter
    @Embedded
    private TechnicianId technicianId;

    public TechnicianInventory() {

    }
}
