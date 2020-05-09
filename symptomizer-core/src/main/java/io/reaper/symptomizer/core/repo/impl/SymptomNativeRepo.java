package io.reaper.symptomizer.core.repo.impl;

import io.reaper.symptomizer.core.repo.NativeRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Repository
public class SymptomNativeRepo implements NativeRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        BigInteger count = (BigInteger) em
                .createNativeQuery("SELECT count(*) FROM symptomizer_db.symptoms")
                .getSingleResult();
        return count.longValue();
    }

    @Override
    public void resetIdAutoIncrement() {
        em.createNativeQuery("ALTER TABLE symptomizer_db.symptoms AUTO_INCREMENT = 1")
                .executeUpdate();
    }

}
