package io.reaper.symptomizer.core.rest;

import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.model.entity.Symptom;
import io.reaper.symptomizer.core.service.CrudService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@RestController
@RequestMapping("/symptoms")
public class SymptomRestController {

    private final CrudService<Symptom> symptomCrudService;

    public SymptomRestController(@Qualifier("symptomServiceImpl") CrudService<Symptom> symptomCrudService) {
        this.symptomCrudService = symptomCrudService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<?>> findAll() {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(symptomCrudService.findAll())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(symptomCrudService.findOne(id))
                .build());
    }

}