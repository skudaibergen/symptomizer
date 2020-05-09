package io.reaper.symptomizer.core.rest;

import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.model.entity.Symptom;
import io.reaper.symptomizer.core.service.CrudService;
import io.reaper.symptomizer.core.service.ImportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author Sanzhar Kudaibergen
 */
@RestController
@RequestMapping("/symptoms")
public class SymptomRestController {

    private final CrudService<Symptom> symptomCrudService;
    private final ImportService importService;

    public SymptomRestController(@Qualifier("symptomServiceImpl") CrudService<Symptom> symptomCrudService,
                                 @Qualifier("symptomServiceImpl") ImportService importService) {
        this.symptomCrudService = symptomCrudService;
        this.importService = importService;
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

    @PostMapping("/import")
    public ResponseEntity<ResponseDto<?>> saveAll(@RequestBody MultipartFile uploadFile) throws Exception {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(importService.importFromFile(uploadFile))
                .build());
    }

}