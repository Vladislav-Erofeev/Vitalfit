package com.example.trainings.services;

import com.example.trainings.domain.entities.Exercise;
import com.example.trainings.domain.entities.MuscleGroups;
import com.example.trainings.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public Exercise save(Exercise exercise) {
        exercise.setModifiedDate(new Date());
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getAllByMuscleGroup(MuscleGroups muscleGroups) {
        return exerciseRepository.findAllByMuscleGroup(muscleGroups);
    }

    public List<Exercise> getAllByName(String name) {
        return exerciseRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Transactional
    public void deleteById(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Transactional
    public Exercise patchById(Long id, Exercise exercise) {
        exercise.setId(id);
        return save(exercise);
    }

    public Exercise getById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Exercise not found"));
    }

}
