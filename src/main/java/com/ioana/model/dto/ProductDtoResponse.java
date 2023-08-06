package com.ioana.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDtoResponse {

    private Date dateAdded;
    private LocalDateTime lastModified;
    private String name;
    private float price;
}
