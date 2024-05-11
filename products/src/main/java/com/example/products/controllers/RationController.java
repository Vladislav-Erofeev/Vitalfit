package com.example.products.controllers;

import com.example.products.domain.dtos.RationDto;
import com.example.products.mappers.RationMapper;
import com.example.products.services.RationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/rations")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RationController {
    private final RationService rationService;
    private final RationMapper rationMapper = RationMapper.INSTANCE;

    @PostMapping
    public RationDto save(@RequestBody RationDto rationDto,
                          @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return rationMapper.toDto(rationService.save(principal.getAttribute("id"),
                rationMapper.toEntity(rationDto)));
    }

    @GetMapping
    public List<RationDto> getAll(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal,
                                  @RequestParam("date") String date) throws ParseException {
        return rationService.getAll(principal.getAttribute("id"), date).stream().map(rationMapper::toDto).toList();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        rationService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public RationDto patchById(@PathVariable("id") Long id,
                               @RequestBody RationDto rationDto) {
        return rationMapper.toDto(rationService.editById(id, rationMapper.toEntity(rationDto)));
    }
}
