package com.hampcoders.electrolink.monitoring.domain.model.aggregates;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportType;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRootNoId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


import java.util.UUID;

@Entity
@Table(name = "reports")
public class Report extends AuditableAbstractAggregateRootNoId<Report> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  @Getter
  private Long id;

  @Getter
  @Embedded
  @AttributeOverride(name = "id", column = @Column(name = "request_id", nullable = false))
  private RequestId requestId;

  @Getter
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "report_type", nullable = false)
  private ReportType reportType;

  @Getter
  @NotBlank
  @Column(name = "description", nullable = false, length = 500)
  private String description;

  protected Report() {}

  public Report(RequestId requestId, ReportType reportType, String description) {
    this.requestId = requestId;
    this.reportType = reportType;
    this.description = description;
  }

 //public ReportId getReportId() {return id;}

}
