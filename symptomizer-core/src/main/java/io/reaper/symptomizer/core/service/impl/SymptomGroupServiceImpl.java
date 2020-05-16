package io.reaper.symptomizer.core.service.impl;

import io.reaper.symptomizer.core.model.entity.Symptom;
import io.reaper.symptomizer.core.repo.SymptomGroupRepo;
import io.reaper.symptomizer.core.repo.SymptomRepo;
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
public class SymptomGroupServiceImpl {

    private final SymptomGroupRepo symptomGroupRepo;
    private final SymptomRepo symptomRepo;

    public Map<String, List<Symptom>> findAllGrouped() {
        Map<Long, List<Symptom>> symptomsGrouped = symptomRepo.findAllByGroupIdNotNull()
                .stream()
                .collect(Collectors.groupingBy(Symptom::getGroupId));

        if (!symptomsGrouped.isEmpty()) {
            Map<String, List<Symptom>> resultMap = new HashMap<>();
            symptomsGrouped.forEach((k, v) -> symptomGroupRepo
                    .findById(k)
                    .ifPresent(symptomGroup -> resultMap.put(symptomGroup.getName(), v)));
            return resultMap;
        }

        return Collections.emptyMap();
    }

}
