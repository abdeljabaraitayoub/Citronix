package org.hidxop.citronix.dto.harvest;

import org.hidxop.citronix.domain.entitiy.Harvest;
import org.hidxop.citronix.dto.harvestDetail.HarvestDetailMapper;
import org.hidxop.citronix.dto.sale.SaleMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HarvestMapper {
    HarvestBasicResponseDto toBasicDto(Harvest harvest);

    List<HarvestBasicResponseDto> toBasicDto(List<Harvest> harvests);

    HarvestDetailedResponseDto toDetailedDto(Harvest harvest);

    List<HarvestDetailedResponseDto> toDetailedDto(List<Harvest> harvests);
//    HarvestCreateRequestDto toDto(Harvest harvest);

    Harvest toEntity(HarvestUpdateRequestDto harvestUpdateRequestDto);
    Harvest toEntity(HarvestCreateRequestDto harvestUpdateRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Harvest partialUpdate(HarvestUpdateRequestDto harvestUpdateRequestDto, @MappingTarget Harvest harvest);
}