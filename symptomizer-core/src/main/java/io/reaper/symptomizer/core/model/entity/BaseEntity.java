package io.reaper.symptomizer.core.model.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Sanzhar Kudaibergen
 */
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "_created_at", nullable = false)
    protected Date createdAt;

}
