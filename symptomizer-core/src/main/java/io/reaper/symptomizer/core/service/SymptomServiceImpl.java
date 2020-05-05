package io.reaper.symptomizer.core.service;

import io.reaper.symptomizer.core.model.entity.Symptom;
import io.reaper.symptomizer.core.repo.SymptomRepo;
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
public class SymptomServiceImpl implements CrudService<Symptom> {

    private final SymptomRepo symptomRepo;

    @Override
    public Symptom findOne(Long id) {
        return symptomRepo.getOne(id);
    }

    @Override
    public List<Symptom> findAll() {
        return symptomRepo.findAll();
    }

    @Override
    public Symptom save(Symptom entity) {
        return symptomRepo.save(entity);
    }

    @Override
    public Symptom update(Symptom entity) {
        return symptomRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        symptomRepo.deleteById(id);
    }

}
