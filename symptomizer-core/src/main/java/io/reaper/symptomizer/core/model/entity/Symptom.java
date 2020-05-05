package io.reaper.symptomizer.core.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Data
@Entity
@Table(name = "symptoms")
@EqualsAndHashCode(callSuper = true)
public class Symptom extends BaseEntity {

    @Column(name = "_code", nullable = false)
    private String code;

}
