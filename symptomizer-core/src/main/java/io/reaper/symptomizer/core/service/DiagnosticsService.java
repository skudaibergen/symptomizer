package io.reaper.symptomizer.core.service;

import io.reaper.symptomizer.core.model.dto.AnamnesisDto;
import io.reaper.symptomizer.core.model.dto.DiagnosisDto;

public interface DiagnosticsService {

    DiagnosisDto predict(AnamnesisDto anamnesis);

}
