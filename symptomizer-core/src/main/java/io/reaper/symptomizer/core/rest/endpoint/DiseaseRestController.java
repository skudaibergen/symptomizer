package io.reaper.symptomizer.core.rest.endpoint;

import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.model.entity.Disease;
import io.reaper.symptomizer.core.service.CrudService;
import io.reaper.symptomizer.core.service.ImportService;
import io.reaper.symptomizer.core.service.PageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final PageService<Disease> diseasePageService;
    private final ImportService importService;
    private final ImportService diseaseSymptomImportService;

    public DiseaseRestController(@Qualifier("diseaseServiceImpl") CrudService<Disease> diseaseCrudService,
                                 @Qualifier("diseaseServiceImpl") PageService<Disease> diseasePageService,
                                 @Qualifier("diseaseServiceImpl") ImportService importService,
                                 @Qualifier("diseaseSymptomAssocServiceImpl") ImportService diseaseSymptomImportService) {
        this.diseaseCrudService = diseaseCrudService;
        this.diseasePageService = diseasePageService;
        this.importService = importService;
        this.diseaseSymptomImportService = diseaseSymptomImportService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<?>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(diseasePageService.findAll(pageable))
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

    @PostMapping("/symptoms/import")
    public ResponseEntity<ResponseDto<?>> saveAssociations(@RequestBody MultipartFile uploadFile) throws Exception {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(diseaseSymptomImportService.importFromFile(uploadFile))
                .build());
    }
}