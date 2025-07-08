package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.commands.CreateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateServiceCommand;

public interface ServiceCommandService {
    Long handle(CreateServiceCommand command);
    void handle(UpdateServiceCommand command);
    void handle(DeleteServiceCommand command);
}
