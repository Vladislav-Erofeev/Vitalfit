package com.example.products.mappers;

import com.example.products.domain.dtos.RationDto;
import com.example.products.domain.entities.Ration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProductMapper.class, PropsMapper.class})
public interface RationMapper {
    RationMapper INSTANCE = Mappers.getMapper(RationMapper.class);

    @Mapping(source = "created", target = "created", qualifiedByName = "stringToDate")
    Ration toEntity(RationDto rationDto);

    @Mapping(source = "created", target = "created", qualifiedByName = "dateToString")
    RationDto toDto(Ration ration);
}
