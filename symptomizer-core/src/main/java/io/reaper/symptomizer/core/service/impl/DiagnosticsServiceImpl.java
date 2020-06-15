package io.reaper.symptomizer.core.service.impl;

import com.google.common.base.Preconditions;
import io.reaper.symptomizer.core.model.dto.AnamnesisDto;
import io.reaper.symptomizer.core.model.dto.DiagnosisDto;
import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.model.entity.Disease;
import io.reaper.symptomizer.core.model.entity.SymptomQuestion;
import io.reaper.symptomizer.core.repo.DiseaseRepo;
import io.reaper.symptomizer.core.repo.SymptomRepo;
import io.reaper.symptomizer.core.repo.impl.DiseaseNativeRepo;
import io.reaper.symptomizer.core.rest.client.MLRestClient;
import io.reaper.symptomizer.core.service.DiagnosticsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Slf4j
@Service
@AllArgsConstructor
public class DiagnosticsServiceImpl implements DiagnosticsService {

    private final DiseaseRepo diseaseRepo;
    private final SymptomRepo symptomRepo;
    private final DiseaseNativeRepo diseaseNativeRepo;
    private final MLRestClient mlRestClient;

    @Override
    public DiagnosisDto predict(AnamnesisDto anamnesis) throws Exception {
        log.info("Predicting by anamnesis: {}", anamnesis);
        ResponseDto<DiagnosisDto> response = mlRestClient.predictDisease(anamnesis);

        Preconditions.checkArgument(response.getData() != null, "ResponseDto data is null");
        Preconditions.checkArgument(response.getData().getPredictedDisease() != null, "ResponseDto data predictedDisease is null");

        String code = response.getData().getPredictedDisease().getCode();
        Disease predictedDisease = diseaseRepo.findByCode(code);

        Map<String, Float> closestPreds = response.getData().getClosestPreds();
        Map<String, Float> closestPredsNames = new LinkedHashMap<>();
        List<Disease> closestPredDiseases = new LinkedList<>();

        if (closestPreds != null) {
            closestPreds.forEach((k, v) -> {
                Disease d = diseaseRepo.findByCode(k);

                if (d != null && !d.getId().equals(predictedDisease.getId())) {
                    closestPredsNames.put(d.getName(), v);
                    closestPredDiseases.add(d);
                }
            });
        }

        DiagnosisDto dto = DiagnosisDto.builder()
                .predictedDisease(predictedDisease)
                .closestPreds(closestPredsNames)
                .tryNum(anamnesis.getTryNum())
                .build();

        if (anamnesis.getTryNum() <= 1) {
            List<SymptomQuestion> questions = new ArrayList<>();

            List<Long> symptomQIds = new ArrayList<>();
            List<Long> diseaseSymptomIds = diseaseNativeRepo.getDiseaseSymptomIds(predictedDisease.getId(), anamnesis.getSymptomIds());

            if (!diseaseSymptomIds.isEmpty()) {
                for (int i = 0; i < diseaseSymptomIds.size() && i < 2; i++) {
                    if (symptomQIds.size() < 10)
                        symptomQIds.add(diseaseSymptomIds.get(i));
                }
            }


            if (!closestPredDiseases.isEmpty()) {
                for (Disease d : closestPredDiseases) {
                    diseaseSymptomIds = diseaseNativeRepo.getDiseaseSymptomIds(d.getId(), anamnesis.getSymptomIds());

                    if (!diseaseSymptomIds.isEmpty()) {
                        for (int i = 0; i < diseaseSymptomIds.size() && i < 2; i++) {
                            if (symptomQIds.size() < 10)
                                symptomQIds.add(diseaseSymptomIds.get(i));
                        }
                    }
                }
            }

            if (!symptomQIds.isEmpty()) {
                for (Long symptomId : symptomQIds) {
                    symptomRepo.findById(symptomId).ifPresent(symptom -> questions.add(SymptomQuestion.builder()
                            .name(generateQuestion(symptom.getName()))
                            .symptomCode(symptom.getCode())
                            .symptomId(symptomId)
                            .build()));
                }
            }

            if (!questions.isEmpty()) {

                dto.setQuestions(questions.stream()
                        .collect(
                                Collectors.collectingAndThen(
                                        Collectors.toCollection(
                                                () -> new TreeSet<>(Comparator.comparingLong(SymptomQuestion::getSymptomId))
                                        ),
                                        ArrayList::new
                                )
                        )
                );
            }
        }

        return dto;
    }

    private String generateQuestion(String symptomName) {
        String[] patterns = new String[] {
                "Do you have %s?",
                "Have you experienced %s?",
                "Was there any %s recently?",
                "Did you suffer from %s?",
                "Did you complain of %s in the past?",
                "Have %s appeared recently?",
                "Have you noticed %s happened?",
                "Have %s happened before?"
        };
        Random random = new Random();

        return String.format(patterns[random.nextInt(patterns.length)], symptomName);
    }

}
