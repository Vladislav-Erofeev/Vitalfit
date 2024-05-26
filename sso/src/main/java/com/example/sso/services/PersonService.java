package com.example.sso.services;

import com.example.sso.domain.entities.Person;
import com.example.sso.domain.entities.Roles;
import com.example.sso.exceptions.PersonAlreadyExistsException;
import com.example.sso.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        person.setRole(Roles.ADMIN);
        return personRepository.save(person);
    }

    public Person getById(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElseThrow(() -> new ObjectNotFoundException(id, "Person not found"));
    }

    @Transactional
    public Person patchById(Long id, Person newPerson) {
        Person person = getById(id);
        newPerson.setPassword(person.getPassword());
        newPerson.setEmail(person.getEmail());
        newPerson.setRole(person.getRole());
        newPerson.setId(person.getId());
        return personRepository.save(newPerson);
    }

}
