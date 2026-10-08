package com.hampcoders.electrolink.profiles.domain.model.aggregates;

import com.hampcoders.electrolink.profiles.domain.model.entities.HomeOwner;
import com.hampcoders.electrolink.profiles.domain.model.entities.Technician;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.EmailAddress;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.PersonName;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.Role;
import com.hampcoders.electrolink.profiles.domain.model.valueobjects.StreetAddress;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;


import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "firstName", column = @Column(name = "first_name", length = 30, nullable = false)),
    @AttributeOverride(name = "lastName", column = @Column(name = "last_name", length = 30, nullable = false))
  })
  private PersonName personName;

  @Embedded
  @AttributeOverride(name = "address", column = @Column(name = "street_address", length = 100, nullable = false))
  private StreetAddress address;

  @Embedded
  @AttributeOverride(name = "address", column = @Column(name = "email", length = 50, nullable = false, unique = true))
  private EmailAddress email;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private Role role;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "home_owner_id")
  private HomeOwner homeOwner;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "technician_id")
  private Technician technician;

  // --- Constructors ---

  protected Profile() {}

  public Profile(PersonName personName, EmailAddress email, StreetAddress address, Role role) {
    this.personName = personName;
    this.email = email;
    this.address = address;
    this.role = role;
  }

  // --- Role assignment ---

  public void assignHomeOwner(HomeOwner homeOwner) {
    if (role != Role.HOMEOWNER) {
      throw new IllegalStateException("Cannot assign HomeOwner. Role must be HOMEOWNER.");
    }
    this.homeOwner = homeOwner;
  }

  public void assignTechnician(Technician technician) {
    if (role != Role.TECHNICIAN) {
      throw new IllegalStateException("Cannot assign Technician. Role must be TECHNICIAN.");
    }
    this.technician = technician;
  }

  // --- Updates ---

  public void updateInformation(PersonName personName, EmailAddress email, StreetAddress address, Role role) {
    this.personName = personName;
    this.email = email;
    this.address = address;
    this.role = role;
  }

  // --- Getters ---

  public PersonName getPersonName() {
    return personName;
  }

  public EmailAddress getEmail() {
    return email;
  }

  public StreetAddress getAddress() {
    return address;
  }

  public Role getRole() {
    return role;
  }

  public HomeOwner getHomeOwner() {
    return homeOwner;
  }

  public Technician getTechnician() {
    return technician;
  }
}
