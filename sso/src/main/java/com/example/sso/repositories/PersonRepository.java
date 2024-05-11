package com.example.sso.repositories;

import com.example.sso.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByEmail(String email);
    Optional<Person> findByEmail(String email);
}
