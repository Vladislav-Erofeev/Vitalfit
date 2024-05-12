package com.example.trainings.domain.dtos;

import java.util.Map;

public record ExecutionDto(Long id, Long personId, Map<String, Integer> repeats,
                           String creationDate, ExerciseDto exercise) {
}
