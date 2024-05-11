package com.example.products.domain.entities;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Ration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ration_id")
    private Long id;
    private Long personId;
    private Integer weight;
    @Enumerated(EnumType.STRING)
    private Datetimes datetime;
    private Date created;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
