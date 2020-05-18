package io.reaper.symptomizer.core.repo;

import io.reaper.symptomizer.core.model.entity.SymptomQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sanzhar Kudaibergen
 */
public interface SymptomQuestionRepo extends JpaRepository<SymptomQuestion, Long> {
}
