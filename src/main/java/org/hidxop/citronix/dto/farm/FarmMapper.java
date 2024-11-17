package org.hidxop.citronix.dto.farm;

import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.dto.field.FieldMapper;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {FieldMapper.class})
public interface FarmMapper {

    FarmBasicResponseDto toBasicDto(Farm farm);
    FarmDetailedResponseDto toDetailedDto(Farm farm);
    Farm toEntity(FarmUpdateRequestDto farmUpdateRequestDto);
    Farm toEntity(FarmCreateRequestDto farmCreateRequestDto);

//    @AfterMapping
//    default void linkFields(@MappingTarget Farm farm) {
//        farm.getFields().forEach(field -> field.setFarm(farm));
//    }


//
//    default List<UUID> fieldsToFieldIds(List<Field> fields) {
//        return fields.stream().map(Field::getId).collect(Collectors.toList());
//    }

}