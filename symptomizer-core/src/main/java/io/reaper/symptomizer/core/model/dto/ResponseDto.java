package io.reaper.symptomizer.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<D> {

    private static final String SUCCESS = "success";

    private String status;

    private D data;

    @Builder
    public ResponseDto(String status,
                       D data) {
        this.status = status == null ? SUCCESS : status;
        this.data = data;
    }
}
