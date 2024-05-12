package com.example.trainings.services;

import com.example.trainings.domain.entities.Execution;
import com.example.trainings.domain.entities.Exercise;
import com.example.trainings.mappers.PropsMapper;
import com.example.trainings.repositories.ExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExecutionService {
    private final ExecutionRepository executionRepository;
    private final ExerciseService exerciseService;

    @Transactional
    public Execution save(Long personId, Execution execution) {
        Exercise exercise = exerciseService.getById(execution.getExercise().getId());
        execution.setPersonId(personId);
        execution.setExercise(exercise);
        return executionRepository.save(execution);
    }

    public List<Execution> getExecutionsByPersonIdAndDate(Long personId, String date) throws ParseException {
        Date startDate = PropsMapper.stringToDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 1);
        return executionRepository.findAllByPersonIdAndDate(personId, startDate, calendar.getTime());
    }

    public Execution getById(Long id) {
        return executionRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Execution not found"));
    }

    @Transactional
    public void deleteById(Long id) {
        executionRepository.deleteById(id);
    }

    @Transactional
    public Execution patchById(Long id, Execution execution) {
        Execution oldExecution = getById(id);
        oldExecution.setRepeats(execution.getRepeats());
        return executionRepository.save(oldExecution);
    }
}
