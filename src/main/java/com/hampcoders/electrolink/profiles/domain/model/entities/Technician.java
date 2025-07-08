package com.hampcoders.electrolink.profiles.domain.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "technicians")
public class Technician {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "certification_code", length = 50)
  private String certificationCode;

  protected Technician() {}

  public Technician(String certificationCode) {
    this.certificationCode = certificationCode;
  }

  public Long getId() {
    return id;
  }

  public String getCertificationCode() {
    return certificationCode;
  }

  public void updateCertification(String newCode) {
    this.certificationCode = newCode;
  }
}
