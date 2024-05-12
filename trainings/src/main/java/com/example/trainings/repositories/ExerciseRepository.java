package com.example.trainings.repositories;

import com.example.trainings.domain.entities.Exercise;
import com.example.trainings.domain.entities.MuscleGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByMuscleGroup(MuscleGroups muscleGroups);

    List<Exercise> findAllByNameContainingIgnoreCase(String name);
}
