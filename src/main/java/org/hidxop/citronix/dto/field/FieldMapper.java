package org.hidxop.citronix.dto.field;

import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.dto.farm.FarmMapper;
import org.hidxop.citronix.dto.tree.TreeMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FieldMapper {
    FieldBasicResponseDto toBasicDto(Field field);
    FieldDetailedResponseDto toDetailedDto(Field field);



}