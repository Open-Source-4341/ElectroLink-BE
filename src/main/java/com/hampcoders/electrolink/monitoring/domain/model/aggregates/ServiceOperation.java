package com.hampcoders.electrolink.monitoring.domain.model.aggregates;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.TechnicianId;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRootNoId;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.OffsetDateTime;

@Entity
@Table(name = "service_operations")
public class ServiceOperation extends AuditableAbstractAggregateRootNoId<ServiceOperation> {

  @EmbeddedId
  @AttributeOverride(name = "id", column = @Column(name = "request_id", nullable = false))
  private RequestId requestId;

  @Getter
  @Column(name = "current_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ServiceStatus currentStatus;

  @Getter
  @Column(name = "started_at", nullable = false)
  private OffsetDateTime startedAt;

  @Getter
  @Column(name = "completed_at")
  private OffsetDateTime completedAt;

  @Getter
  @Embedded
  @AttributeOverride(name = "id", column = @Column(name = "technician_id", nullable = false))
  private TechnicianId technicianId;

  public ServiceOperation() {
    super();
  }

  public ServiceOperation(RequestId requestId, TechnicianId technicianId,
                          OffsetDateTime startedAt, OffsetDateTime completedAt, ServiceStatus currentStatus) {
    this.requestId = requestId;
    this.technicianId = technicianId;
    this.startedAt = startedAt;
    this.completedAt = completedAt;
    this.currentStatus = currentStatus;
  }

  public void complete(OffsetDateTime completedAt) {
    this.completedAt = completedAt;
  }

  public void updateStatus(ServiceStatus status) {
    this.currentStatus = status;
  }
  public ServiceStatus getStatus() {
    return currentStatus;
  }

  public RequestId getRequestId() {
    return requestId;
  }

  public void setCompletedAt(OffsetDateTime completedAt) {
    this.completedAt = completedAt;
  }

  public OffsetDateTime getCompletedAt() {
    return this.completedAt;
  }
}