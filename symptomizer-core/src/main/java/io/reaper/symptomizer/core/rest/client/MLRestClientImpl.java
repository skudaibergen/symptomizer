package io.reaper.symptomizer.core.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.reaper.symptomizer.core.model.dto.AnamnesisDto;
import io.reaper.symptomizer.core.model.dto.DiagnosisDto;
import io.reaper.symptomizer.core.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author Sanzhar Kudaibergen
 */
@Component
public class MLRestClientImpl implements MLRestClient {

    private RestTemplate _template;

    private final String apiUrl;
    private final ObjectMapper objectMapper;

    public MLRestClientImpl(@Value("${symptomizer.ml.api.url}") String apiUrl,
                            ObjectMapper objectMapper) {
        this.apiUrl = apiUrl;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseDto<DiagnosisDto> predictDisease(AnamnesisDto dto) throws Exception {
        ResponseEntity<ResponseDto> response = template().postForEntity("/predict/disease", dto, ResponseDto.class);

        if (response.getBody() != null && response.getBody().getData() != null) {
            ResponseDto<DiagnosisDto> body = response.getBody();
            body.setData(objectMapper.convertValue(body.getData(), DiagnosisDto.class));
            return body;
        }

        throw new Exception("Response body is null");
    }

    private RestTemplate template() {
        if (_template == null)
            _template = buildTemplate();

        return _template;
    }

    private RestTemplate buildTemplate() {
        return new RestTemplateBuilder()
                .rootUri(apiUrl)
                .build();
    }

}
