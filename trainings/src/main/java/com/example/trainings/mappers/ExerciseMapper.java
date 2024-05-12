package com.example.trainings.mappers;

import com.example.trainings.domain.dtos.ExerciseDto;
import com.example.trainings.domain.entities.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PropsMapper.class)
public interface ExerciseMapper {
    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "dateToString")
    ExerciseDto toDto(Exercise exercise);

    @Mapping(source = "modifiedDate", target = "modifiedDate", ignore = true)
    Exercise toEntity(ExerciseDto exerciseDto);
}
