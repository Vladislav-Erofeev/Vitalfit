package com.example.sso.mappers;

import com.example.sso.domain.dtos.ParameterDto;
import com.example.sso.domain.entities.Parameter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PropsMapper.class})
public interface ParameterMapper {
    ParameterMapper INSTANCE = Mappers.getMapper(ParameterMapper.class);

    @Mapping(source = "measurementDate", target = "measurementDate", qualifiedByName = "getStringFromDate")
    ParameterDto toDto(Parameter parameter);

    @Mapping(source = "measurementDate", target = "measurementDate", ignore = true)
    Parameter toEntity(ParameterDto parameterDto);
}
