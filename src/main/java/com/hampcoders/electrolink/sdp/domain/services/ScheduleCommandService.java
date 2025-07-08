package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.commands.CreateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateScheduleCommand;

public interface ScheduleCommandService {
    Long handle(CreateScheduleCommand command);
    void handle(UpdateScheduleCommand command);
    void handle(DeleteScheduleCommand command);
}
