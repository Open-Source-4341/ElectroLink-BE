package com.hampcoders.electrolink.monitoring.application.internal.commandservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.AddReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportPhotoId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportType;
import com.hampcoders.electrolink.monitoring.domain.services.IReportCommandService;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportRepository;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ServiceOperationRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class ReportCommandServiceImpl implements IReportCommandService {

  private final ServiceOperationRepository serviceOperationRepository;
  private final ReportRepository reportRepository;
  private final EntityManager entityManager;

  public ReportCommandServiceImpl(ReportRepository reportRepository, EntityManager entityManager, ServiceOperationRepository serviceOperationRepository) {
    this.reportRepository = reportRepository;
    this.entityManager = entityManager;
    this.serviceOperationRepository = serviceOperationRepository;
  }

  @Override
  public Long handle(AddReportCommand command) {
    // Validar que el requestId esté asociado a una ServiceOperation existente
    serviceOperationRepository.findByRequestId(command.requestId())
        .orElseThrow(() -> new IllegalArgumentException("No ServiceOperation found with RequestId: " + command.requestId().getId()));

    // Crear el reporte si pasa la validación
    var report = new Report(
        command.requestId(),
        ReportType.valueOf(String.valueOf(command.reportType())),
        command.description()
    );
    reportRepository.save(report);
    return report.getId();
  }

  @Override
  public void handle(DeleteReportCommand command) {
    var report = reportRepository.findById(command.reportId())
        .orElseThrow(() -> new IllegalArgumentException("Report not found"));
    reportRepository.delete(report);
  }

  @Override
  public ReportPhotoId handle(AddPhotoCommand command) {
    var reportPhoto = new ReportPhoto(
        command.reportPhotoId(),
        command.reportId(),
        command.url()
    );
    entityManager.persist(reportPhoto);
    return reportPhoto.reportPhotoId();
  }
}