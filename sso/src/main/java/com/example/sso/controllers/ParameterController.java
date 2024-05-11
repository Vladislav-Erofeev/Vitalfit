package com.example.sso.controllers;

import com.example.sso.domain.dtos.ParameterDto;
import com.example.sso.mappers.ParameterMapper;
import com.example.sso.services.ParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parameters")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ParameterController {
    private final ParameterService parameterService;
    private final ParameterMapper parameterMapper = ParameterMapper.INSTANCE;

    @PostMapping
    public ParameterDto save(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal,
                             @RequestBody ParameterDto parameterDto) {
        return parameterMapper.toDto(parameterService.save(principal.getAttribute("id"),
                parameterMapper.toEntity(parameterDto)));
    }

    @GetMapping
    public List<ParameterDto> getAll(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return parameterService.getAllByPersonId(principal.getAttribute("id"))
                .stream().map(parameterMapper::toDto).toList();
    }
}
