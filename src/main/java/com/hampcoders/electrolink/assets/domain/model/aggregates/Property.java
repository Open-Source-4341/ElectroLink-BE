package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.commands.CreatePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdatePropertyCommand;
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
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "region_name"))
    })
    private Region region;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "district_name"))
    })
    private District district;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private PropertyStatus status;*/

    @Embedded
    private PropertyPhoto photo;

    protected Property() {
    }

    public Property(CreatePropertyCommand command) {
        this.ownerId = command.ownerId();
        this.address = command.address();
        this.region = command.region();
        this.district = command.district();
        //this.status = PropertyStatus.getDefaultPropertyStatus();
        this.photo = null;
    }

    public void setPhoto(String photoUrl) {
        if (photoUrl == null || photoUrl.trim().isEmpty()) {
            this.photo = null;
            return;
        }
        this.photo = new PropertyPhoto(photoUrl);
    }

    public void update(UpdatePropertyCommand command) {
        this.address = command.address();
        this.region = command.region();
        this.district = command.district();
    }

    /*public void updateAddress(Address newAddress) {
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
    }*/


}