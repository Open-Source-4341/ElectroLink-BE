package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.entities.PropertyStatus;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "properties")
@Getter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private OwnerId ownerId;

    @Embedded
    private Address address;

    @Embedded
    private Region region;

    @Embedded
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private PropertyStatus status;

    @Embedded
    private PropertyPhoto photo;

    protected Property() {
    }

    public Property(OwnerId ownerId, Address address, Region region, District district) {
        this.ownerId = ownerId;
        this.address = address;
        this.region = region;
        this.district = district;
        this.status = PropertyStatus.getDefaultPropertyStatus();
        this.photo = null;
    }

    public Property(UUID id, OwnerId ownerId, Address address, Region region, District district, PropertyStatus status) {
        this.id = id;
        this.ownerId = ownerId;
        this.address = address;
        this.region = region;
        this.district = district;
        this.status = status;
        this.photo = null;
    }

    public void setPhoto(String photoUrl) {
        if (photoUrl == null || photoUrl.trim().isEmpty()) {
            this.photo = null;
            return;
        }
        this.photo = new PropertyPhoto(photoUrl);
    }

    public void updateAddress(Address newAddress) {
        if (this.status.getName() == PropertyStatuses.INACTIVE) {
            throw new IllegalStateException("Cannot update the address of an inactive property.");
        }
        this.address = newAddress;
    }

    public void deactivate() {
        if (this.status.getName() == PropertyStatuses.INACTIVE) {
            return;
        }
        this.status = PropertyStatus.toPropertyStatusFromName("INACTIVE");
    }


}