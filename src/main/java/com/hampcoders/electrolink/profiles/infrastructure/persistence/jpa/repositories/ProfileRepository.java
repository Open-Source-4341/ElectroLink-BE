package com.hampcoders.electrolink.profiles.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.profiles.domain.model.aggregates.Profile;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Optional<Profile> findByPersonName_FirstNameAndPersonName_LastName(String firstName, String lastName);

  Optional<Profile> findByEmail_Address(String email);

  List<Profile> findByRole(Role role);

  boolean existsByEmail_Address(String email);

  boolean existsByEmail_AddressAndIdIsNot(String email, Long id);
}
