package com.example.sso.controllers;

import com.example.sso.domain.dtos.ErrorResponse;
import com.example.sso.domain.dtos.PersonDto;
import com.example.sso.domain.dtos.RegistrationRequest;
import com.example.sso.exceptions.PersonAlreadyExistsException;
import com.example.sso.mappers.ParameterMapper;
import com.example.sso.mappers.PersonMapper;
import com.example.sso.services.ParameterService;
import com.example.sso.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    private final ParameterService parameterService;
    private final ParameterMapper parameterMapper = ParameterMapper.INSTANCE;

    @PostMapping("/register")
    public PersonDto save(@RequestBody RegistrationRequest registrationRequest) throws PersonAlreadyExistsException {
        return personMapper.toDto(personService.save(personMapper.toEntity(registrationRequest)));
    }

    @PreAuthorize("isAuthenticated()")
    @PostAuthorize("returnObject.email == principal.name")
    @GetMapping("/profile")
    public PersonDto getProfile(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        PersonDto personDto = personMapper.toDto(personService.getById(principal.getAttribute("id")));
        personDto.setParameter(parameterMapper.toDto(parameterService.getLatestByPersonId(personDto.getId())));
        return personDto;
    }

    @PreAuthorize("isAuthenticated() and #personDto.email == principal.name")
    @PatchMapping("/profile")
    public PersonDto patchById(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal,
                               @RequestBody PersonDto personDto) {
        return personMapper.toDto(personService.patchById(principal.getAttribute("id"),
                personMapper.toEntity(personDto)));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptions(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
