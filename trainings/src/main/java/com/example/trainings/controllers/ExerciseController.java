package com.example.trainings.controllers;

import com.example.trainings.domain.dtos.ErrorResponse;
import com.example.trainings.domain.dtos.ExerciseDto;
import com.example.trainings.domain.entities.MuscleGroups;
import com.example.trainings.mappers.ExerciseMapper;
import com.example.trainings.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper = ExerciseMapper.INSTANCE;

    @GetMapping(params = "query")
    public List<ExerciseDto> getAllByName(@RequestParam("query") String query) {
        return exerciseService.getAllByName(query).stream().map(exerciseMapper::toDto).toList();
    }

    @GetMapping(params = "group")
    public List<ExerciseDto> getAllBuGroup(@RequestParam("group") MuscleGroups muscleGroups) {
        return exerciseService.getAllByMuscleGroup(muscleGroups).stream().map(exerciseMapper::toDto).toList();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ExerciseDto save(@RequestBody ExerciseDto exerciseDto) {
        return exerciseMapper.toDto(exerciseService.save(exerciseMapper.toEntity(exerciseDto)));
    }

    @GetMapping("/{id}")
    public ExerciseDto getById(@PathVariable("id") Long id) {
        return exerciseMapper.toDto(exerciseService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        exerciseService.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public ExerciseDto patchById(@PathVariable("id") Long id, @RequestBody ExerciseDto exerciseDto) {
        return exerciseMapper.toDto(exerciseService.patchById(id, exerciseMapper.toEntity(exerciseDto)));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> objectNotFound(ObjectNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
