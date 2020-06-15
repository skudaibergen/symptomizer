package io.reaper.symptomizer.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.reaper.symptomizer.core.model.entity.Disease;
import io.reaper.symptomizer.core.model.entity.SymptomQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosisDto {

    private Disease predictedDisease;

    private Map<String, Float> closestPreds;

    private List<SymptomQuestion> questions;

    private int tryNum;

}
