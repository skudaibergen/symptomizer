package io.reaper.symptomizer.core.rest;

import io.reaper.symptomizer.core.model.dto.AnamnesisDto;
import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.service.DiagnosticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@RestController
@RequestMapping("/diagnostics")
@AllArgsConstructor
public class DiagnosticsRestController {

    private final DiagnosticsService diagnosticsService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<?>> predict(@RequestBody AnamnesisDto anamnesisDto) {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(diagnosticsService.predict(anamnesisDto))
                .build());
    }

}
