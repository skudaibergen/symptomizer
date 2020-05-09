package io.reaper.symptomizer.core.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Entity
@Table(name = "symptoms")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Symptom extends BaseEntity {

    @Column(name = "_code", nullable = false)
    private String code;

    @Column(name = "_name", nullable = false)
    private String name;

}
