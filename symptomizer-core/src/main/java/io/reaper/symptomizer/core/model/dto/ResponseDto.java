package io.reaper.symptomizer.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

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
    private Items items;
    private D data;

    @Builder
    public ResponseDto(String status,
                       D data) {
        this.status = status == null ? SUCCESS : status;

        if (data instanceof Page) {
            Page page = (Page) data;
            this.items = new Items(
                    (int) page.getTotalElements(),
                    page.getSize(),
                    page.getNumber()
            );
        }

        this.data = data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Items {

        private Integer total;
        private Integer size;
        private Integer page;

    }


}
