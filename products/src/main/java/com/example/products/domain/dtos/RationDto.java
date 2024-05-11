package com.example.products.domain.dtos;

import com.example.products.domain.entities.Datetimes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RationDto {
    private Long id;
    private Integer weight;
    private Datetimes datetime;
    private String created;
    private ProductDto product;
}
