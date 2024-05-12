package com.example.trainings.controllers;

import com.example.trainings.domain.dtos.ExecutionDto;
import com.example.trainings.mappers.ExecutionMapper;
import com.example.trainings.services.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/executions")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ExecutionController {
    private final ExecutionService executionService;
    private final ExecutionMapper executionMapper = ExecutionMapper.INSTANCE;

    @GetMapping
    public List<ExecutionDto> getAll(@RequestParam("date") String date,
                                     @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) throws ParseException {
        return executionService.getExecutionsByPersonIdAndDate(principal.getAttribute("id"), date)
                .stream().map(executionMapper::toDto).toList();
    }

    @PostMapping
    public ExecutionDto save(@RequestBody ExecutionDto executionDto,
                             @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return executionMapper.toDto(executionService.save(principal.getAttribute("id"),
                executionMapper.toEntity(executionDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        executionService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ExecutionDto getById(@PathVariable("id") Long id) {
        return executionMapper.toDto(executionService.getById(id));
    }

    @PatchMapping("/{id}")
    public ExecutionDto patchById(@PathVariable("id") Long id,
                                  @RequestBody ExecutionDto executionDto) {
        return executionMapper.toDto(executionService.patchById(id, executionMapper.toEntity(executionDto)));
    }
}
