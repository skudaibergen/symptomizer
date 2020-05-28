package io.reaper.symptomizer.core.rest.endpoint;

import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.model.entity.Symptom;
import io.reaper.symptomizer.core.service.CrudService;
import io.reaper.symptomizer.core.service.ImportService;
import io.reaper.symptomizer.core.service.PageService;
import io.reaper.symptomizer.core.service.impl.SymptomGroupServiceImpl;
import io.reaper.symptomizer.core.service.impl.SymptomQuestionServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    private final PageService<Symptom> symptomPageService;
    private final ImportService importService;
    private final SymptomGroupServiceImpl symptomGroupService;
    private final SymptomQuestionServiceImpl symptomQuestionService;

    public SymptomRestController(@Qualifier("symptomServiceImpl") CrudService<Symptom> symptomCrudService,
                                 @Qualifier("symptomServiceImpl") PageService<Symptom> symptomPageService,
                                 @Qualifier("symptomServiceImpl") ImportService importService,
                                 SymptomGroupServiceImpl symptomGroupService,
                                 SymptomQuestionServiceImpl symptomQuestionService) {
        this.symptomCrudService = symptomCrudService;
        this.symptomPageService = symptomPageService;
        this.importService = importService;
        this.symptomGroupService = symptomGroupService;
        this.symptomQuestionService = symptomQuestionService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<?>> findAll(@RequestParam(required = false) boolean grouped,
                                                  @PageableDefault Pageable pageable) {
        Object data = grouped ?
                symptomGroupService.findAllGrouped() :
                symptomPageService.findAll(pageable);

        return ResponseEntity.ok(ResponseDto.builder()
                .data(data)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(symptomCrudService.findOne(id))
                .build());
    }

    @GetMapping("/questions")
    public ResponseEntity<ResponseDto<?>> findQuestions() {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(symptomQuestionService.findAll())
                .build());
    }

    @PostMapping("/import")
    public ResponseEntity<ResponseDto<?>> saveAll(@RequestBody MultipartFile uploadFile) throws Exception {
        return ResponseEntity.ok(ResponseDto.builder()
                .data(importService.importFromFile(uploadFile))
                .build());
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportAll() throws Exception {
        return new ResponseEntity<>(importService.exportToFile(), HttpStatus.OK);
    }

}