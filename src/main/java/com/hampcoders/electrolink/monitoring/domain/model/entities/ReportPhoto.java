package com.hampcoders.electrolink.monitoring.domain.model.entities;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportPhotoId;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "report_photos")
public class ReportPhoto {

  @EmbeddedId
  @AttributeOverride(name = "id", column = @Column(name = "report_photo_id", nullable = false))
  private ReportPhotoId id = new ReportPhotoId();

  @Getter
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "report_id", nullable = false))
  private ReportId reportId;

  @Getter
  @Column(name = "url", nullable = false)
  private String url;

  public ReportPhoto(ReportPhotoId reportPhotoId, ReportId reportId, String url) {
    this.id = reportPhotoId;
    this.reportId = reportId;
    this.url = url;
  }

  protected ReportPhoto() {}

  public ReportPhotoId reportPhotoId() {
    return id;
  }

  public ReportPhotoId getId() {
    return id;
  }

}
