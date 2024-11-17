package org.hidxop.citronix.dto.harvestDetail;

import org.hidxop.citronix.domain.entitiy.HarvestDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HarvestDetailMapper {
    HarvestDetailBasicResponseDto toBasicDto(HarvestDetail harvestDetail);

    HarvestDetailDetailedResponseDto toDetailedDto(HarvestDetail harvestDetail);

}