package org.hidxop.citronix.dto.harvest;

import org.hidxop.citronix.domain.entitiy.Harvest;
import org.hidxop.citronix.dto.harvestDetail.HarvestDetailMapper;
import org.hidxop.citronix.dto.sale.SaleMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {HarvestDetailMapper.class, SaleMapper.class})
public interface HarvestMapper {
    HarvestBasicResponseDto toBasicDto(Harvest harvest);

    HarvestDetailedResponseDto toDetailedDto(Harvest harvest);

    @AfterMapping
    default void linkHarvestDetails(@MappingTarget Harvest harvest) {
        harvest.getHarvestDetails().forEach(harvestDetail -> harvestDetail.setHarvest(harvest));
    }

    @AfterMapping
    default void linkSale(@MappingTarget Harvest harvest) {
        harvest.getSale().forEach(sale -> sale.setHarvest(harvest));
    }


}