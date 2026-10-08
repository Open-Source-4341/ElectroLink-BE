package com.hampcoders.electrolink.monitoring.interfaces.rest;

import com.hampcoders.electrolink.monitoring.application.internal.commandservices.RatingCommandServiceImpl;
import com.hampcoders.electrolink.monitoring.application.internal.queryservices.RatingQueryServiceImpl;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllRatingsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingsByRequestIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetRatingsByTechnicianIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RatingId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateRatingResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.RatingResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateRatingResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.CreateRatingCommandFromResourceAssembler;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.RatingResourceFromEntityAssembler;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.UpdateRatingCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Ratings", description = "Rating Management Endpoints")
@RestController
@RequestMapping("/api/v1/ratings")
public class RatingsController {

  private final RatingCommandServiceImpl commandService;
  private final RatingQueryServiceImpl queryService;

  public RatingsController(RatingCommandServiceImpl commandService, RatingQueryServiceImpl queryService) {
    this.commandService = commandService;
    this.queryService = queryService;
  }

  @Operation(summary = "Create a new rating", responses = {
      @ApiResponse(responseCode = "201", description = "Rating created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
  })
  @PostMapping
  public ResponseEntity<Long> createRating(@RequestBody CreateRatingResource resource) {
    var command = CreateRatingCommandFromResourceAssembler.toCommandFromResource(resource);
    var ratingId = commandService.handle(command);
    return new ResponseEntity<>(ratingId, HttpStatus.CREATED);
  }

  @Operation(summary = "Update a rating", responses = {
      @ApiResponse(responseCode = "200", description = "Rating updated successfully"),
      @ApiResponse(responseCode = "404", description = "Rating not found", content = @Content)
  })
  @PutMapping
  public ResponseEntity<Void> updateRating(@RequestBody UpdateRatingResource resource) {
    var command = UpdateRatingCommandFromResourceAssembler.toCommandFromResource(resource);
    commandService.handle(command);
    return ResponseEntity.ok().build();
  }
  @Operation(summary = "Delete a rating", responses = {
      @ApiResponse(responseCode = "204", description = "Rating deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Rating not found", content = @Content)
  })
  @DeleteMapping("/{ratingId}")
  public ResponseEntity<Void> deleteRating(
      @Parameter(description = "Id of the rating") @PathVariable Long ratingId
  ) {
    var command = new DeleteRatingCommand(new RatingId(ratingId));
    commandService.handle(command);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Get rating by ID", responses = {
      @ApiResponse(responseCode = "200", description = "Found rating", content = @Content(schema = @Schema(implementation = RatingResource.class))),
      @ApiResponse(responseCode = "404", description = "Rating not found", content = @Content)
  })
  @GetMapping("/{ratingId}")
  public ResponseEntity<RatingResource> getRatingById(
      @Parameter(description = "Id of the rating") @PathVariable Long ratingId
  ) {
    var query = new GetRatingByIdQuery(ratingId);
    var rating = queryService.handle(query)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found"));

    return ResponseEntity.ok(RatingResourceFromEntityAssembler.toResourceFromEntity(rating));
  }

  @Operation(summary = "Get all ratings")
  @GetMapping
  public ResponseEntity<List<RatingResource>> getAllRatings() {
    var ratings = queryService.handle(new GetAllRatingsQuery());
    var resources = ratings.stream().map(RatingResourceFromEntityAssembler::toResourceFromEntity).toList();
    return ResponseEntity.ok(resources);
  }

  @Operation(summary = "Get ratings by technician ID")
  @GetMapping("/technicians/{technicianId}")
  public ResponseEntity<List<RatingResource>> getRatingsByTechnicianId(
      @Parameter(description = "Technician ID") @PathVariable Long technicianId
  ) {
    var ratings = queryService.handle(new GetRatingsByTechnicianIdQuery(technicianId));
    var resources = ratings.stream().map(RatingResourceFromEntityAssembler::toResourceFromEntity).toList();
    return ResponseEntity.ok(resources);
  }

  @Operation(summary = "Get ratings by request ID")
  @GetMapping("/requests/{requestId}")
  public ResponseEntity<List<RatingResource>> getRatingsByRequestId(
      @Parameter(description = "Request ID") @PathVariable Long requestId
  ) {
    var ratings = queryService.handle(new GetRatingsByRequestIdQuery(requestId));
    var resources = ratings.stream().map(RatingResourceFromEntityAssembler::toResourceFromEntity).toList();
    return ResponseEntity.ok(resources);
  }
}