package io.reaper.symptomizer.core.repo;

import io.reaper.symptomizer.core.model.entity.SymptomGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sanzhar Kudaibergen
 */
public interface SymptomGroupRepo extends JpaRepository<SymptomGroup, Long> {
}
