package io.reaper.symptomizer.core.rest;

import io.reaper.symptomizer.core.model.dto.ResponseDto;
import io.reaper.symptomizer.core.model.entity.Disease;
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
@RequestMapping("/diseases")
public class DiseaseRestController {

//    private final CrudService<Disease> diseaseCrudService;
//
//    public DiseaseRestController(@Qualifier("diseaseServiceImpl") CrudService<Symptom> diseaseCrudService) {
//        this.diseaseCrudService = diseaseCrudService;
//    }
//
//    @GetMapping("")
//    public ResponseEntity<ResponseDto<?>> findAll() {
//        return ResponseEntity.ok(ResponseDto.builder()
//                .data(diseaseCrudService.findAll())
//                .build());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseDto<?>> findOne(@PathVariable Long id) {
//        return ResponseEntity.ok(ResponseDto.builder()
//                .data(diseaseCrudService.findOne(id))
//                .build());
//    }

}