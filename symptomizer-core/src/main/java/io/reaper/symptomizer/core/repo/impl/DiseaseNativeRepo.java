package io.reaper.symptomizer.core.repo.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import io.reaper.symptomizer.core.repo.NativeRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Sanzhar Kudaibergen
 */
@Repository
public class DiseaseNativeRepo implements NativeRepo {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        BigInteger count = (BigInteger) em
                .createNativeQuery("SELECT count(*) FROM symptomizer_db.diseases")
                .getSingleResult();
        return count.longValue();
    }

    @Override
    public void resetIdAutoIncrement() {
        em.createNativeQuery("ALTER TABLE symptomizer_db.diseases AUTO_INCREMENT = 1")
                .executeUpdate();
    }

    public List<Long> getDiseaseSymptomIds(Long diseaseId, List<Long> symptomIds) {
        try {
            List<BigInteger> res = em.createNativeQuery("SELECT `symptom_id` " +
                    "FROM disease_symptoms_map " +
                    "WHERE `disease_id` = :diseaseId AND " +
                    "symptom_id NOT IN (:symptomIds)")
                    .setParameter("diseaseId", diseaseId)
                    .setParameter("symptomIds", symptomIds)
                    .getResultList();

            if (res != null && !res.isEmpty())
                return res.stream()
                        .map(BigInteger::longValue)
                        .collect(Collectors.toList());
        } catch (Exception ignored) {}

        return new ArrayList<>();
    }

    public void saveAssociation(String diseaseCode, String symptomCode) {
        try {
            Long diseaseId = getDiseaseId(diseaseCode);
            Long symptomId = getSymptomId(symptomCode);

            em.createNativeQuery("INSERT INTO disease_symptoms_map VALUE (:diseaseId, :symptomId)")
                    .setParameter("diseaseId", diseaseId)
                    .setParameter("symptomId", symptomId)
                    .executeUpdate();
        } catch (NoSuchEntityException | NoResultException e) {
            e.printStackTrace();
        }
    }

    public Long getDiseaseId(String diseaseCode) {
        return ((BigInteger) em.createNativeQuery("SELECT `_id` FROM diseases WHERE `_code` = :diseaseCode")
                .setParameter("diseaseCode", diseaseCode)
                .getSingleResult()).longValue();
    }

    public Long getSymptomId(String symptomCode) {
        return ((BigInteger) em.createNativeQuery("SELECT `_id` FROM symptoms WHERE `_code` = :symptomCode")
                .setParameter("symptomCode", symptomCode)
                .getSingleResult()).longValue();
    }

}
