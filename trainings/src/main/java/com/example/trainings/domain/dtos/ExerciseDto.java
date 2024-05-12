package com.example.trainings.domain.dtos;

import com.example.trainings.domain.entities.MuscleGroups;

public record ExerciseDto(Long id, String name, MuscleGroups muscleGroup, String description,
                          String image, String example, String modifiedDate) {
}
