package io.reaper.symptomizer.core.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Data
@Builder
public class ResponseDto<D> {

    private String status = "success";

    private D data;

}
