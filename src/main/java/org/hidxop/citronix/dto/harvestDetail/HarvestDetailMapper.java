package org.hidxop.citronix.dto.harvestDetail;

import org.hidxop.citronix.domain.entitiy.HarvestDetail;
import org.hidxop.citronix.dto.harvest.HarvestDetailsCreateRequestDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HarvestDetailMapper {
    HarvestDetailBasicResponseDto toBasicDto(HarvestDetail harvestDetail);

    HarvestDetailDetailedResponseDto toDetailedDto(HarvestDetail harvestDetail);

    @Mapping(source = "fieldId", target = "tree.field.id")
    @Mapping(source = "harvestDate", target = "harvest.harvestDate")
    HarvestDetail toEntity( HarvestDetailsCreateRequestDto harvestDetailsCreateRequestDto);

    @InheritInverseConfiguration(name = "toEntity")
    HarvestDetailsCreateRequestDto toDto(HarvestDetail harvestDetail);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    HarvestDetail partialUpdate(HarvestDetailsCreateRequestDto harvestDetailsCreateRequestDto, @MappingTarget HarvestDetail harvestDetail);
}