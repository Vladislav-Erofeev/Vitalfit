package com.example.sso.services;

import com.example.sso.domain.entities.Parameter;
import com.example.sso.domain.entities.Person;
import com.example.sso.repositories.ParameterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParameterService {
    private final ParameterRepository parameterRepository;
    private final PersonService personService;

    @Transactional
    public Parameter save(Long personId, Parameter parameter) {
        Person person = personService.getById(personId);
        parameter.setPerson(person);
        parameter.setMeasurementDate(new Date());
        return parameterRepository.save(parameter);
    }

    public List<Parameter> getAllByPersonId(Long personId) {
        return parameterRepository.findAllByPersonId(personId);
    }

    public Parameter getLatestByPersonId(Long personId) {
        Optional<Parameter> optionalParameter = parameterRepository.findByPersonIdOrderByIdDescLimit1(personId);
        return optionalParameter.orElse(null);
    }
}
