package com.ioana.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseEntity<ID extends Serializable> implements Serializable {

    @Id
    @SequenceGenerator(
            name = "entity_sequence",
            sequenceName = "entity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "entity_sequence"
    )
    private ID id;

    @Column(name = "date_added")
    protected LocalDateTime dateAdded;

    @Column(name = "last_modified")
    protected LocalDateTime lastModified;

    @PrePersist
    private void onCreate() {
        dateAdded = LocalDateTime.now();
        lastModified = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        lastModified = LocalDateTime.now();
    }
}
