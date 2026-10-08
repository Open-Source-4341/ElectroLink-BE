package com.hampcoders.electrolink.monitoring.application.internal.commandservices;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;
import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportPhotoId;
import com.hampcoders.electrolink.monitoring.domain.services.IReportPhotoCommandService;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportPhotoRepository;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ReportPhotoCommandServiceImpl implements IReportPhotoCommandService {

  private final ReportRepository reportRepository;
  private final ReportPhotoRepository reportPhotoRepository;

  public ReportPhotoCommandServiceImpl(ReportRepository reportRepository,
                                       ReportPhotoRepository reportPhotoRepository) {
    this.reportRepository = reportRepository;
    this.reportPhotoRepository = reportPhotoRepository;
  }

  @Transactional
  @Override
  public Long handle(AddPhotoCommand command) {
    Report report = reportRepository.findById(command.reportId())
            .orElseThrow(() -> new IllegalArgumentException("Report not found with ID: " + command.reportId().getId()));

    ReportPhoto photo = new ReportPhoto(new ReportPhotoId(), command.reportId(), command.url());
    reportPhotoRepository.save(photo);
    return photo.getId().getId();
  }
}
