package io.reaper.symptomizer.core.service;

import java.util.List;

/**
 *
 * @author Sanzhar Kudaibergen
 */
public interface CrudService<T> {

    T findOne(Long id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void delete(Long id);

}
