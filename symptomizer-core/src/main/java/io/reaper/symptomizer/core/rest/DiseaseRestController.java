package io.reaper.symptomizer.core.rest;

import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.model.entity.Disease;
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
@RequestMapping("/diseases")
public class DiseaseRestController {

    private final CrudService<Disease> diseaseCrudService;
    private final ImportService importService;

    public DiseaseRestController(@Qualifier("diseaseServiceImpl") CrudService<Disease> diseaseCrudService,
                                 @Qualifier("diseaseServiceImpl") ImportService importService) {
        this.diseaseCrudService = diseaseCrudService;
        this.importService = importService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<?>> findAll() {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(diseaseCrudService.findAll())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(diseaseCrudService.findOne(id))
                .build());
    }

    @PostMapping("/import")
    public ResponseEntity<ResponseDto<?>> saveAll(@RequestBody MultipartFile uploadFile) throws Exception {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(importService.importFromFile(uploadFile))
                .build());
    }

}