package io.reaper.symptomizer.core.service.impl;

import io.reaper.symptomizer.core.model.dto.ReportDto;
import io.reaper.symptomizer.core.model.entity.Disease;
import io.reaper.symptomizer.core.repo.DiseaseRepo;
import io.reaper.symptomizer.core.repo.NativeRepo;
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
import java.util.Optional;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Slf4j
@Service
public class DiseaseServiceImpl implements CrudService<Disease>, ImportService {

    private final DiseaseRepo diseaseRepo;
    private final NativeRepo nativeRepo;

    public DiseaseServiceImpl(DiseaseRepo diseaseRepo,
                              @Qualifier("diseaseNativeRepo") NativeRepo nativeRepo) {
        this.diseaseRepo = diseaseRepo;
        this.nativeRepo = nativeRepo;
    }

    @Override
    public Disease findOne(Long id) {
        return diseaseRepo.findById(id).orElse(new Disease());

    }

    @Override
    public List<Disease> findAll() {
        return diseaseRepo.findAll();
    }

    @Override
    @Transactional
    public Disease save(Disease entity) {
        return diseaseRepo.save(entity);
    }

    @Override
    @Transactional
    public List<Disease> save(List<Disease> entities) {
        return diseaseRepo.saveAll(entities);
    }

    @Override
    @Transactional
    public Disease update(Disease entity) {
        return diseaseRepo.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        diseaseRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        diseaseRepo.deleteAll();
    }

    @Override
    @Transactional
    public ReportDto importFromFile(MultipartFile file) throws IOException {
        assertIsCsv(file);

        List<Disease> diseases;
        String row;
        try (InputStream is = new ByteArrayInputStream(file.getBytes());
             InputStreamReader isReader = new InputStreamReader(is);
             BufferedReader buffReader = new BufferedReader(isReader)) {
            diseases = new ArrayList<>();

            while ((row = buffReader.readLine()) != null) {
                // String[] data = row.split(","); // multi-column
                // do something with the data
                String code = row.replace(" ", "_");
                String name = StringUtils.capitalize(row.substring(14));

                diseases.add(Disease.builder()
                        .code(code)
                        .name(name)
                        .build());
            }
        }

        long saved = 0, total = nativeRepo.count();
        if (!diseases.isEmpty()) {
            if (total > 0) {
                deleteAll();
                nativeRepo.resetIdAutoIncrement();
            }

            saved = save(diseases).size();
        }

        return ReportDto.builder()
                .imported((int) saved)
                .deleted((int) total)
                .build();
    }
}
