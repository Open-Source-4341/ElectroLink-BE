package com.hampcoders.electrolink.profiles.interfaces.rest;

import com.hampcoders.electrolink.profiles.domain.model.commands.DeleteProfileCommand;
import com.hampcoders.electrolink.profiles.domain.model.queries.*;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;
import com.hampcoders.electrolink.profiles.domain.services.ProfileCommandService;
import com.hampcoders.electrolink.profiles.domain.services.ProfileQueryService;
import com.hampcoders.electrolink.profiles.interfaces.rest.resources.CreateProfileResource;
import com.hampcoders.electrolink.profiles.interfaces.rest.resources.ProfileResource;
import com.hampcoders.electrolink.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.hampcoders.electrolink.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.hampcoders.electrolink.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {

  private final ProfileQueryService profileQueryService;
  private final ProfileCommandService profileCommandService;

  public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
    this.profileQueryService = profileQueryService;
    this.profileCommandService = profileCommandService;
  }

  @PostMapping
  public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
    var command = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
    var profileId = profileCommandService.handle(command);

    var optionalProfile = profileQueryService.handle(new GetProfileByIdQuery(profileId));
    return optionalProfile.map(profile ->
        new ResponseEntity<>(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile), HttpStatus.CREATED))
      .orElseGet(() -> ResponseEntity.badRequest().build());
  }

  @GetMapping
  public ResponseEntity<List<ProfileResource>> getAllProfiles() {
    var profiles = profileQueryService.handle(new GetAllProfilesQuery());
    var resources = profiles.stream()
      .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
      .collect(Collectors.toList());
    return ResponseEntity.ok(resources);
  }

  @GetMapping("/{profileId}")
  public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
    var optionalProfile = profileQueryService.handle(new GetProfileByIdQuery(profileId));
    return optionalProfile.map(profile ->
        ResponseEntity.ok(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile)))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{profileId}")
  public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long profileId, @RequestBody ProfileResource resource) {
    var command = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
    var optionalProfile = profileCommandService.handle(command);
    return optionalProfile.map(profile ->
        ResponseEntity.ok(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile)))
      .orElseGet(() -> ResponseEntity.badRequest().build());
  }

  @DeleteMapping("/{profileId}")
  public ResponseEntity<?> deleteProfile(@PathVariable Long profileId) {
    profileCommandService.handle(new DeleteProfileCommand(profileId));
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public ResponseEntity<List<ProfileResource>> searchProfiles(
    @RequestParam(required = false) String email,
    @RequestParam(required = false) Role role,
    @RequestParam(required = false) String firstName,
    @RequestParam(required = false) String lastName
  ) {
    List<ProfileResource> results;

    if (email != null) {
      var optional = profileQueryService.handle(new GetProfileByEmailQuery(email));
        results = optional.map(profile -> List.of(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile))).orElseGet(List::of);
    } else if (role != null) {
      results = profileQueryService.handle(new GetProfilesByRoleQuery(role)).stream()
        .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    } else if (firstName != null && lastName != null) {
      var optional = profileQueryService.handle(new GetProfileByFullNameQuery(firstName, lastName));
        results = optional.map(profile -> List.of(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile))).orElseGet(List::of);
    } else {
      results = profileQueryService.handle(new GetAllProfilesQuery()).stream()
        .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    }

    return ResponseEntity.ok(results);
  }
}
