package com.example.trainings.domain.entities;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.Map;

@Entity
@Getter
@Setter
public class Execution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "execution_id")
    private Long id;

    private Long personId;
    @Type(JsonBinaryType.class)
    private Map<String, Integer> repeats;
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    private Exercise exercise;
}
