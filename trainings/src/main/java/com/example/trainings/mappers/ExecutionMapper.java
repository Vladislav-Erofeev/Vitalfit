package com.example.trainings.mappers;

import com.example.trainings.domain.dtos.ExecutionDto;
import com.example.trainings.domain.entities.Execution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PropsMapper.class, ExerciseMapper.class})
public interface ExecutionMapper {
    ExecutionMapper INSTANCE = Mappers.getMapper(ExecutionMapper.class);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateToString")
    ExecutionDto toDto(Execution execution);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "stringToDate")
    Execution toEntity(ExecutionDto executionDto);
}
