package com.example.sso.domain.dtos;

import com.example.sso.domain.entities.Genders;

public record RegistrationRequest(String email, String password, Integer phone, String name,
                                  String surname, String lastname, String birthdate, String address,
                                  Genders gender, String description) {
}
