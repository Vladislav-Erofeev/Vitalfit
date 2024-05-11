package com.example.products.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Integer calories;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
}
