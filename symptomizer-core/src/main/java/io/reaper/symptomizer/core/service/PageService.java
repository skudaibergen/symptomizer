package io.reaper.symptomizer.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Sanzhar Kudaibergen
 */
public interface PageService<T> {

    Page<T> findAll(Pageable pageable);

}
