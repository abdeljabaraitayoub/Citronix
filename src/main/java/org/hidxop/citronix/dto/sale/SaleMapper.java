package org.hidxop.citronix.dto.sale;

import org.hidxop.citronix.domain.entitiy.Sale;
import org.hidxop.citronix.dto.harvest.HarvestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SaleMapper {
    SaleBasicResponseDto toBasicDto(Sale sale);
    List<SaleBasicResponseDto> toBasicDto(List<Sale> sales);

    SaleDetailedResponseDto toDetailedDto(Sale sale);
    List<SaleDetailedResponseDto> toDetailedDto(List<Sale> sales);
    @Mapping(source = "harvestId", target = "harvest.id")
    Sale toEntity(SaleCreateRequestDto saleCreateRequestDto);

    @Mapping(source = "harvest.id", target = "harvestId")
    SaleCreateRequestDto toDto(Sale sale);

    @Mapping(source = "harvestId", target = "harvest.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sale partialUpdate(SaleUpdateRequestDto saleUpdateRequestDto, @MappingTarget Sale sale);
}