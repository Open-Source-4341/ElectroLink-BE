package com.hampcoders.electrolink.monitoring.domain.model.aggregates;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRootNoId;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Getter;

@Entity
@Table(name = "ratings")
public class Rating extends AuditableAbstractAggregateRootNoId<Rating> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rating_id", nullable = false, updatable = false)
  @Getter
  private Long id;

  @Getter
  @Embedded
  @AttributeOverride(name = "id", column = @Column(name = "request_id", nullable = false))
  private RequestId requestId;

  @Getter
  @NotNull
  @Min(1)
  @Max(5)
  @Column(name = "score", nullable = false)
  private Integer score;

  @Getter
  @Size(max = 300)
  @Column(name = "comment")
  private String comment;

  @Getter
  @NotBlank
  @Column(name = "rater_id", nullable = false)
  private String raterId;

  @Getter
  @Embedded
  @AttributeOverride(name = "id", column = @Column(name = "technician_id", nullable = false))
  private TechnicianId technicianId;

  protected Rating() {
    // Required by JPA
  }

  public Rating(RequestId requestId, Integer score, String comment, String raterId, TechnicianId technicianId) {
    this.requestId = requestId;
    this.score = score;
    this.comment = comment;
    this.raterId = raterId;
    this.technicianId = technicianId;
  }

  public Long getRatingId() {
    return id;
  }

  public void updateScore(int score) {
    this.score = score;
  }

  public void updateComment(String comment) {
    this.comment = comment;
  }
}
