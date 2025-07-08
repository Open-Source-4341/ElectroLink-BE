package com.hampcoders.electrolink.sdp.interfaces.rest;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ScheduleAggregate;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindSchedulesByTechnicianIdQuery;
import com.hampcoders.electrolink.sdp.domain.services.ScheduleCommandService;
import com.hampcoders.electrolink.sdp.domain.services.ScheduleQueryService;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateScheduleResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.UpdateScheduleResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ScheduleController {

    private final ScheduleCommandService commandService;
    private final ScheduleQueryService queryService;

    public ScheduleController(ScheduleCommandService commandService, ScheduleQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping("/technicians/{technicianId}/schedules")
    public ResponseEntity<List<ScheduleAggregate>> getByTechnician(@PathVariable String technicianId) {
        var query = new FindSchedulesByTechnicianIdQuery(technicianId);
        var result = queryService.handle(query);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/schedules")
    public ResponseEntity<Long> create(@RequestBody CreateScheduleResource resource) {
        var command = new CreateScheduleCommand(resource.technicianId(), resource.day(), resource.startTime(), resource.endTime());
        Long createdId = commandService.handle(command);
        return ResponseEntity.ok(createdId);
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> update(@PathVariable Long scheduleId, @RequestBody UpdateScheduleResource resource) {
        var command = new UpdateScheduleCommand(scheduleId, resource.technicianId(),resource.day(), resource.startTime(), resource.endTime());
        commandService.handle(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> delete(@PathVariable Long scheduleId) {
        var command = new DeleteScheduleCommand(scheduleId);
        commandService.handle(command);
        return ResponseEntity.ok().build();
    }
}
