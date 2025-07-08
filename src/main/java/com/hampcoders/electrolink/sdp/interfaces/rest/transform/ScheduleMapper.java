package com.hampcoders.electrolink.sdp.interfaces.rest.transform;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ScheduleAggregate;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateScheduleResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.ScheduleResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.UpdateScheduleResource;

public class ScheduleMapper {

    public static ScheduleAggregate toModel(CreateScheduleResource resource) {
        return new ScheduleAggregate(
                resource.technicianId(),
                resource.day(),
                resource.startTime(),
                resource.endTime()
        );
    }

    public static ScheduleResource toResource(ScheduleAggregate model) {
        return new ScheduleResource(
                model.getId(),
                model.getTechnicianId(),
                model.getDay(),
                model.getStartTime(),
                model.getEndTime()
        );
    }

    public static void updateModel(ScheduleAggregate model, UpdateScheduleResource resource) {
        model.updateFrom(
                resource.day(),
                resource.startTime(),
                resource.endTime()
        );
    }
}
