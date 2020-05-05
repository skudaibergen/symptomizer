package io.reaper.symptomizer.core.service;

import io.reaper.symptomizer.core.model.entity.Disease;
import io.reaper.symptomizer.core.repo.DiseaseRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Slf4j
@Service
@AllArgsConstructor
public class DiseaseServiceImpl implements CrudService<Disease> {

    private final DiseaseRepo diseaseRepo;

    @Override
    public Disease findOne(Long id) {
        return diseaseRepo.getOne(id);
    }

    @Override
    public List<Disease> findAll() {
        return diseaseRepo.findAll();
    }

    @Override
    public Disease save(Disease entity) {
        return diseaseRepo.save(entity);
    }

    @Override
    public Disease update(Disease entity) {
        return diseaseRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        diseaseRepo.deleteById(id);
    }

}
