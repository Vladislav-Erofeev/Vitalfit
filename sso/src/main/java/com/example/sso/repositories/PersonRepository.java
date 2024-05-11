package com.example.sso.repositories;

import com.example.sso.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByEmail(String email);
}
