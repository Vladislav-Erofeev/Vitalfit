package com.example.sso.services;

import com.example.sso.domain.entities.Person;
import com.example.sso.domain.entities.Roles;
import com.example.sso.exceptions.PersonAlreadyExistsException;
import com.example.sso.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Person save(Person person) throws PersonAlreadyExistsException {
        if (personRepository.existsByEmail(person.getEmail()))
            throw new PersonAlreadyExistsException(person.getEmail());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(Roles.USER);
        return personRepository.save(person);
    }
}
