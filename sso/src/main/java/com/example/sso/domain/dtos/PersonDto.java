package com.example.sso.domain.dtos;

import com.example.sso.domain.entities.Genders;
import com.example.sso.domain.entities.Roles;

public record PersonDto(Long id, String email, Integer phone, String name,
                        String surname, String lastname, String birthdate, String address,
                        Genders gender, String description, Roles role) {
}
