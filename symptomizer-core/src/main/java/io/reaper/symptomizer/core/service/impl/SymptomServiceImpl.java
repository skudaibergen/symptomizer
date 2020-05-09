package io.reaper.symptomizer.core.service.impl;

import io.reaper.symptomizer.core.model.dto.ReportDto;
import io.reaper.symptomizer.core.model.entity.Symptom;
import io.reaper.symptomizer.core.repo.NativeRepo;
import io.reaper.symptomizer.core.repo.SymptomRepo;
import io.reaper.symptomizer.core.service.CrudService;
import io.reaper.symptomizer.core.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Slf4j
@Service
public class SymptomServiceImpl implements CrudService<Symptom>, ImportService {

    private final SymptomRepo symptomRepo;
    private final NativeRepo symptomNativeRepo;

    public SymptomServiceImpl(SymptomRepo symptomRepo,
                              @Qualifier("symptomNativeRepo") NativeRepo symptomNativeRepo) {
        this.symptomRepo = symptomRepo;
        this.symptomNativeRepo = symptomNativeRepo;
    }

    @Override
    public Symptom findOne(Long id) {
        return symptomRepo.getOne(id);
    }

    @Override
    public List<Symptom> findAll() {
        return symptomRepo.findAll();
    }

    @Override
    @Transactional
    public Symptom save(Symptom entity) {
        return symptomRepo.save(entity);
    }

    @Override
    @Transactional
    public List<Symptom> save(List<Symptom> entities) {
        return symptomRepo.saveAll(entities);
    }

    @Override
    @Transactional
    public Symptom update(Symptom entity) {
        return symptomRepo.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        symptomRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        symptomRepo.deleteAll();
    }

    @Override
    @Transactional
    public ReportDto importFromFile(MultipartFile file) throws IOException {
        assertIsCsv(file);

        List<Symptom> symptoms;
        String row;
        try (InputStream is = new ByteArrayInputStream(file.getBytes());
             InputStreamReader isReader = new InputStreamReader(is);
             BufferedReader buffReader = new BufferedReader(isReader)) {
            symptoms = new ArrayList<>();

            while ((row = buffReader.readLine()) != null) {
                // String[] data = row.split(","); // multi-column
                // do something with the data
                String code = row.replace(" ", "_");
                String name = StringUtils.capitalize(row.substring(14));

                symptoms.add(Symptom.builder()
                        .code(code)
                        .name(name)
                        .build());
            }
        }

        long saved = 0, total = symptomNativeRepo.count();
        if (!symptoms.isEmpty()) {
            if (total > 0) {
                deleteAll();
                symptomNativeRepo.resetIdAutoIncrement();
            }

            saved = save(symptoms).size();
        }

        return ReportDto.builder()
                .imported((int) saved)
                .deleted((int) total)
                .build();
    }

}
