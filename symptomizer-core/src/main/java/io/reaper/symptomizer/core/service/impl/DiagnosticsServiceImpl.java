package io.reaper.symptomizer.core.service.impl;

import io.reaper.symptomizer.core.model.dto.AnamnesisDto;
import io.reaper.symptomizer.core.model.dto.DiagnosisDto;
import io.reaper.symptomizer.core.repo.DiseaseRepo;
import io.reaper.symptomizer.core.service.DiagnosticsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Slf4j
@Service
@AllArgsConstructor
public class DiagnosticsServiceImpl implements DiagnosticsService {

    private final DiseaseRepo diseaseRepo;

    @Override
    public DiagnosisDto predict(AnamnesisDto anamnesis) {
        log.info("Predicting by anamnesis: {}", anamnesis);
        return DiagnosisDto.builder()
                .predictedDisease(diseaseRepo.findById(1L).get())
                .build();
    }

}
