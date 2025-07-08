package com.hampcoders.electrolink.sdp.domain.model.entities;

import com.hampcoders.electrolink.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Bill extends AuditableModel {

    private String billingPeriod;
    private double energyConsumed;
    private double amountPaid;
    private String billImageUrl;

    public Bill(String billingPeriod, double energyConsumed, double amountPaid, String billImageUrl) {
        this.billingPeriod = billingPeriod;
        this.energyConsumed = energyConsumed;
        this.amountPaid = amountPaid;
        this.billImageUrl = billImageUrl;
    }
}
