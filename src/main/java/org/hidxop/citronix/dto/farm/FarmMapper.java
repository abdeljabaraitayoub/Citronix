package org.hidxop.citronix.dto.farm;

import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.dto.field.FieldMapper;
import org.mapstruct.*;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {FieldMapper.class})
public interface FarmMapper {

    FarmBasicResponseDto toBasicDto(Farm farm);
    List<FarmBasicResponseDto> toBasicDto(List<Farm> farms);
    FarmDetailedResponseDto toDetailedDto(Farm farm);
    List<FarmDetailedResponseDto> toDetailedDto(List<Farm> farms);
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