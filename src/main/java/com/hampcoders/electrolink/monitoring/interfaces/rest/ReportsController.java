package com.hampcoders.electrolink.monitoring.interfaces.rest;

import com.hampcoders.electrolink.monitoring.application.internal.commandservices.ReportCommandServiceImpl;
import com.hampcoders.electrolink.monitoring.application.internal.queryservices.ReportQueryServiceImpl;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllReportsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportsByRequestIdQuery;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ReportResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {

  private final ReportCommandServiceImpl commandService;
  private final ReportQueryServiceImpl queryService;

  public ReportsController(ReportCommandServiceImpl commandService, ReportQueryServiceImpl queryService) {
    this.commandService = commandService;
    this.queryService = queryService;
  }

  @Operation(summary = "Create a report")
  @PostMapping
  public ResponseEntity<Long> createReport(@RequestBody CreateReportResource resource) {
    var command = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
    var reportId = commandService.handle(command);
    return new ResponseEntity<>(reportId, HttpStatus.CREATED);
  }

  @Operation(summary = "Delete a report")
  @DeleteMapping("/{reportId}")
  public ResponseEntity<Void> deleteReport(
          @Parameter(description = "Id of the report") @PathVariable Long reportId
  ) {
    var command = new DeleteReportCommand(reportId);
    commandService.handle(command);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Get report by ID")
  @GetMapping("/{reportId}")
  public ResponseEntity<ReportResource> getReportById(
          @Parameter(description = "Id of the report") @PathVariable Long reportId
  ) {
    var query = new GetReportByIdQuery(reportId);
    var report = queryService.handle(query)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));

    return ResponseEntity.ok(ReportResourceFromEntityAssembler.toResourceFromEntity(report));
  }

  @Operation(summary = "Get all reports")
  @GetMapping
  public ResponseEntity<List<ReportResource>> getAllReports() {
    var reports = queryService.handle(new GetAllReportsQuery());
    var resources = reports.stream().map(ReportResourceFromEntityAssembler::toResourceFromEntity).toList();
    return ResponseEntity.ok(resources);
  }

  @Operation(summary = "Get reports by request ID")
  @GetMapping("/requests/{requestId}")
  public ResponseEntity<List<ReportResource>> getReportsByRequestId(
          @Parameter(description = "Request ID") @PathVariable Long requestId
  ) {
    var query = new GetReportsByRequestIdQuery(requestId);
    var reports = queryService.handle(query);
    var resources = reports.stream().map(ReportResourceFromEntityAssembler::toResourceFromEntity).toList();
    return ResponseEntity.ok(resources);
  }
}
