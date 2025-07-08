package com.hampcoders.electrolink.monitoring.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable {

  private Long id;

  public RatingId() {}

  public RatingId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RatingId)) return false;
    RatingId that = (RatingId) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}