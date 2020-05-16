package io.reaper.symptomizer.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.reaper.symptomizer.core.model.entity.Disease;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosisDto {

    private Disease predictedDisease;

}
