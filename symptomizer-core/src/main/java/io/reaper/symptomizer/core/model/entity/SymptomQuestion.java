package io.reaper.symptomizer.core.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@Entity
@Table(name = "symptom_questions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SymptomQuestion extends BaseEntity {

    @Column(name = "_name", nullable = false)
    private String name;

    @Transient
    private transient Boolean positive;

}
