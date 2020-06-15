package io.reaper.symptomizer.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.reaper.symptomizer.core.model.Sex;
import io.reaper.symptomizer.core.model.entity.Symptom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnamnesisDto {

    private Integer age;
    private Sex sex;
    private List<Symptom> symptoms;
    private List<Long> symptomIds;
    private List<String> symptomCodes;
    private int tryNum;

}
