package io.reaper.symptomizer.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDto {

    private Integer imported;
    private Integer deleted;

}
