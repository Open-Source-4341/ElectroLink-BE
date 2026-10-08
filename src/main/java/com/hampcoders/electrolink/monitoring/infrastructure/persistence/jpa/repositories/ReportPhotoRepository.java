package com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportPhotoRepository extends JpaRepository<ReportPhoto, Long> {
}