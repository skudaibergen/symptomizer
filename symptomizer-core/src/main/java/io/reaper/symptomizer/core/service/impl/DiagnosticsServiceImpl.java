package io.reaper.symptomizer.core.service.impl;

import com.google.common.base.Preconditions;
import io.reaper.symptomizer.core.model.dto.AnamnesisDto;
import io.reaper.symptomizer.core.model.dto.DiagnosisDto;
import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.repo.DiseaseRepo;
import io.reaper.symptomizer.core.rest.client.MLRestClient;
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
    private final MLRestClient mlRestClient;

    @Override
    public DiagnosisDto predict(AnamnesisDto anamnesis) throws Exception {
        log.info("Predicting by anamnesis: {}", anamnesis);
        ResponseDto<DiagnosisDto> response = mlRestClient.predictDisease(anamnesis);

        Preconditions.checkArgument(response.getData() != null, "ResponseDto data is null");
        Preconditions.checkArgument(response.getData().getPredictedDisease() != null, "ResponseDto data predictedDisease is null");

        return DiagnosisDto.builder()
                .predictedDisease(diseaseRepo.findByCode(response.getData().getPredictedDisease().getCode()))
//                .questions()
                .build();
    }

}
