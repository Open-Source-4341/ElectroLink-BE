package com.hampcoders.electrolink.sdp.domain.model.aggregates;

import com.hampcoders.electrolink.sdp.domain.model.entities.Bill;
import com.hampcoders.electrolink.sdp.domain.model.entities.Photo;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateRequestResource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Request extends com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot<Request> {

    private String clientId;
    private String technicianId;
    private String propertyId;
    private String serviceId;

    private String problemDescription;
    private LocalDate scheduledDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdAt", column = @Column(name = "bill_created_at")),
            @AttributeOverride(name = "updatedAt", column = @Column(name = "bill_updated_at"))
    })
    private Bill bill;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "request_id")
    private List<Photo> photos = new ArrayList<>();

    public Request(String clientId, String technicianId, String propertyId, String serviceId,
                   String problemDescription, LocalDate scheduledDate, Bill bill, List<Photo> photos) {
        this.clientId = clientId;
        this.technicianId = technicianId;
        this.propertyId = propertyId;
        this.serviceId = serviceId;
        this.problemDescription = problemDescription;
        this.scheduledDate = scheduledDate;
        this.bill = bill;
        this.photos = photos != null ? photos : new ArrayList<>();
    }
    public void setId(Long id) {
        super.setId(id);
    }
    public void updateFrom(CreateRequestResource resource) {
        this.clientId = resource.clientId();
        this.technicianId = resource.technicianId();
        this.propertyId = resource.propertyId();
        this.serviceId = resource.serviceId();
        this.problemDescription = resource.problemDescription();
        this.scheduledDate = resource.scheduledDate();

        this.bill = new Bill(
                resource.bill().billingPeriod(),
                resource.bill().energyConsumed(),
                resource.bill().amountPaid(),
                resource.bill().billImageUrl()
        );

        this.photos.clear();
        if (resource.photos() != null) {
            resource.photos().forEach(photoResource -> {
                this.photos.add(new Photo(photoResource.photoId(), photoResource.url()));
            });
        }
    }
}
