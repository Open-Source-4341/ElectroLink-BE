package com.hampcoders.electrolink.monitoring.interfaces.rest;

import com.hampcoders.electrolink.monitoring.domain.services.IReportPhotoCommandService;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportPhotoResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.CreateReportPhotoCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/photos")
public class ReportPhotoController {

  private final IReportPhotoCommandService photoCommandService;

  public ReportPhotoController(IReportPhotoCommandService photoCommandService) {
    this.photoCommandService = photoCommandService;
  }

  @PostMapping
  public ResponseEntity<?> addPhoto(@RequestBody CreateReportPhotoResource resource) {
    var command = CreateReportPhotoCommandFromResourceAssembler.toCommandFromResource(resource);
    Long photoId = photoCommandService.handle(command);
    return ResponseEntity.created(URI.create("/api/v1/photos/" + photoId)).body(resource);
  }
}