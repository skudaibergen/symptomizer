package io.reaper.symptomizer.core.repo;

import io.reaper.symptomizer.core.model.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Sanzhar Kudaibergen
 */
public interface SymptomRepo extends JpaRepository<Symptom, Long> {

    List<Symptom> findAllByGroupIdNotNull();

}
