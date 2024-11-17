package org.hidxop.citronix.dto.harvest;

import org.hidxop.citronix.domain.entitiy.Harvest;
import org.hidxop.citronix.dto.harvestDetail.HarvestDetailMapper;
import org.hidxop.citronix.dto.sale.SaleMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HarvestMapper {
    HarvestBasicResponseDto toBasicDto(Harvest harvest);

    HarvestDetailedResponseDto toDetailedDto(Harvest harvest);

}