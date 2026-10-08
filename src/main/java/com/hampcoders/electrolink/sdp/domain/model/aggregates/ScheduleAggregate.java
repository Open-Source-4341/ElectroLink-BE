package com.hampcoders.electrolink.sdp.domain.model.aggregates;

import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ScheduleAggregate extends AuditableAbstractAggregateRoot<ScheduleAggregate> {

    private String technicianId;

    private String day;
    private String startTime;
    private String endTime;

    public ScheduleAggregate(String technicianId, String day, String startTime, String endTime) {
        this.technicianId = technicianId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void updateFrom(String day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
