package com.hampcoders.electrolink.monitoring.domain.model.commands;

import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportId;
import com.hampcoders.electrolink.monitoring.domain.model.valueObjects.ReportPhotoId;

public record AddPhotoCommand(ReportPhotoId reportPhotoId, ReportId reportId, String url) {

}