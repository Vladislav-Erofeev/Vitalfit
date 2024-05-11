package com.example.sso.controllers;

import com.example.sso.domain.dtos.ErrorResponse;
import com.example.sso.domain.dtos.PersonDto;
import com.example.sso.domain.dtos.RegistrationRequest;
import com.example.sso.exceptions.PersonAlreadyExistsException;
import com.example.sso.mappers.PersonMapper;
import com.example.sso.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @PreAuthorize("permitAll()")
    @PostMapping
    public PersonDto save(@RequestBody RegistrationRequest registrationRequest) throws PersonAlreadyExistsException {
        return personMapper.toDto(personService.save(personMapper.toEntity(registrationRequest)));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptions(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
