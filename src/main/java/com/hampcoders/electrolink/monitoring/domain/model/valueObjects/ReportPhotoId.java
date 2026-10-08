package com.hampcoders.electrolink.monitoring.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReportPhotoId implements Serializable {

  private Long id;

  public ReportPhotoId() {}

  public ReportPhotoId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ReportPhotoId)) return false;
    ReportPhotoId that = (ReportPhotoId) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}