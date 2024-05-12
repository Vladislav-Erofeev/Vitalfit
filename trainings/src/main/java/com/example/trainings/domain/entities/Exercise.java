package com.example.trainings.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private MuscleGroups muscleGroup;
    private String description;
    private String image;
    private String example;
    private Date modifiedDate;

    @OneToMany(mappedBy = "exercise")
    private List<Execution> executions;
}
