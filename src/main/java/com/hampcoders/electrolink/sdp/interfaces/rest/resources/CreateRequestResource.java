package com.hampcoders.electrolink.sdp.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record CreateRequestResource(
        String clientId,
        String technicianId,
        String propertyId,
        String serviceId,
        String problemDescription,
        LocalDate scheduledDate,
        BillResource bill,
        List<PhotoResource> photos
) {
    public record BillResource(
            String billingPeriod,
            double energyConsumed,
            double amountPaid,
            String billImageUrl
    ) {}

    public record PhotoResource(
            String photoId,
            String url
    ) {}
}
