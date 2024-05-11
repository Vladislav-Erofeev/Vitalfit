package com.example.sso.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private Integer phone;
    private String name;
    private String surname;
    private String lastname;
    @CreationTimestamp
    private Date birthdate;
    private String address;
    @Enumerated(EnumType.STRING)
    private Genders gender;
    private String image;
    private String description;
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "person")
    private List<Parameter> parameters;
}
