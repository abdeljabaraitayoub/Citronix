package org.hidxop.citronix.dto.field;

import org.hidxop.citronix.domain.entitiy.Field;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FieldMapper {
    FieldBasicResponseDto toBasicDto(Field field);
    FieldDetailedResponseDto toDetailedDto(Field field);
    List<FieldBasicResponseDto> toBasicDto(List<Field> fields);
    List<FieldDetailedResponseDto> toDetailedDto(List<Field> fields);

    @Mapping(source = "farmId", target = "farm.id")
    Field toEntity(FieldUpdateRequestDto fieldUpdateRequestDto);

    @Mapping(source = "farmId", target = "farm.id")
    Field toEntity(FieldCreateRequestDto fieldCreateRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "farmId", target = "farm.id")
    void partialUpdate(FieldUpdateRequestDto fieldUpdateRequestDto,@MappingTarget Field field);

}