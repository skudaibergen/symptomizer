package io.reaper.symptomizer.core.repo;

import io.reaper.symptomizer.core.model.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sanzhar Kudaibergen
 */
public interface DiseaseRepo extends JpaRepository<Disease, Long> {
}
