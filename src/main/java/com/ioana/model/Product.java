package com.ioana.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product extends BaseEntity<Long> {

    private String name;
    private float price;

    public Product(Date dateAdded, LocalDateTime lastModified, String name, float price) {
        this.dateAdded = dateAdded;
        this.lastModified = lastModified;
        this.name = name;
        this.price = price;
    }
}
