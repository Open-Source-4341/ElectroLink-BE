package com.hampcoders.electrolink.assets.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.*;

@Entity
@Table(name = "property_status")
@With
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private PropertyStatuses name;

    public PropertyStatus(PropertyStatuses name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static PropertyStatus getDefaultPropertyStatus() {
        return new PropertyStatus(PropertyStatuses.DEFAULT);
    }


    public static PropertyStatus toPropertyStatusFromName(String name) {
        return new PropertyStatus(PropertyStatuses.valueOf(name));
    }

}
