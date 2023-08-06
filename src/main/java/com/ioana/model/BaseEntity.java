package com.ioana.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
    protected Date dateAdded;

    @Column(name = "last_modified")
    protected LocalDateTime lastModified;

    @PrePersist
    private void onCreate() {
        dateAdded = new Date();
        lastModified = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        lastModified = LocalDateTime.now();
    }
}
