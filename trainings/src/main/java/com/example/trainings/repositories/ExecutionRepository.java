package com.example.trainings.repositories;

import com.example.trainings.domain.entities.Execution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExecutionRepository extends JpaRepository<Execution, Long> {

    @Query(value = "select * from execution e join exercise ex on e.exercise_id = ex.id" +
            " where person_id = :personId and creation_date >= :startDate and creation_date < :endDate", nativeQuery = true)
    List<Execution> findAllByPersonIdAndDate(Long personId, Date startDate, Date endDate);
}
