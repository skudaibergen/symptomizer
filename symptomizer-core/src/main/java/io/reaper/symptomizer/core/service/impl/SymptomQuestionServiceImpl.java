package io.reaper.symptomizer.core.service.impl;

import io.reaper.symptomizer.core.model.entity.SymptomQuestion;
import io.reaper.symptomizer.core.repo.SymptomQuestionRepo;
import io.reaper.symptomizer.core.service.CrudService;
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
public class SymptomQuestionServiceImpl implements CrudService<SymptomQuestion> {

    private final SymptomQuestionRepo questionRepo;

    @Override
    public SymptomQuestion findOne(Long id) {
        return questionRepo.findById(id).orElse(null);
    }

    @Override
    public List<SymptomQuestion> findAll() {
        return questionRepo.findAll();
    }

    @Override
    public SymptomQuestion save(SymptomQuestion entity) {
        return null;
    }

    @Override
    public List<SymptomQuestion> save(List<SymptomQuestion> entities) {
        return null;
    }

    @Override
    public SymptomQuestion update(SymptomQuestion entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

    }

}
