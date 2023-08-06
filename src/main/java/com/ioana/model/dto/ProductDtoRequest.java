package com.ioana.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDtoRequest {

    private String name;
    private float price;
}
