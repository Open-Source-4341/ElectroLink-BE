package com.hampcoders.electrolink.profiles.domain.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "home_owners")
public class HomeOwner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "additional_info", length = 100)
  private String additionalInfo;

  protected HomeOwner() {}

  public HomeOwner(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public Long getId() {
    return id;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void updateInfo(String newInfo) {
    this.additionalInfo = newInfo;
  }
}
