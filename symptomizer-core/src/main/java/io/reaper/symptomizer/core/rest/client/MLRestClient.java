package io.reaper.symptomizer.core.rest.client;

import io.reaper.symptomizer.core.model.dto.AnamnesisDto;
import io.reaper.symptomizer.core.model.dto.DiagnosisDto;
import io.reaper.symptomizer.core.model.dto.ResponseDto;


/**
 *
 * @author Sanzhar Kudaibergen
 */
public interface MLRestClient {

    ResponseDto<DiagnosisDto> predictDisease(AnamnesisDto dto) throws Exception;

}
