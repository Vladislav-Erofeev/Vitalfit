package com.example.sso.mappers;

import com.example.sso.domain.dtos.PersonDto;
import com.example.sso.domain.dtos.RegistrationRequest;
import com.example.sso.domain.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PropsMapper.class})
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "birthdate", target = "birthdate", qualifiedByName = "getDateFromString")
    Person toEntity(RegistrationRequest registrationRequest);

    @Mapping(source = "birthdate", target = "birthdate", qualifiedByName = "getStringFromDate")
    PersonDto toDto(Person person);
}
