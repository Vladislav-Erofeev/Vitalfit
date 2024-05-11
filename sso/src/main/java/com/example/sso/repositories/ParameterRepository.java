package com.example.sso.repositories;

import com.example.sso.domain.entities.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    List<Parameter> findAllByPersonId(Long id);

    @Query(value = "select * from parameter where person_id = :personId order by id desc limit 1",
            nativeQuery = true)
    Optional<Parameter> findByPersonIdOrderByIdDescLimit1(Long personId);
}
