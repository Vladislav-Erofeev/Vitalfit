package com.example.products.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer calories;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;

    @OneToMany
    private List<Ration> rations;
}
