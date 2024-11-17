package org.hidxop.citronix.dto.harvestDetail;

import org.hidxop.citronix.domain.entitiy.HarvestDetail;
import org.hidxop.citronix.dto.harvest.HarvestMapper;
import org.hidxop.citronix.dto.tree.TreeMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TreeMapper.class, HarvestMapper.class})
public interface HarvestDetailMapper {
    HarvestDetailBasicResponseDto toBasicDto(HarvestDetail harvestDetail);

    HarvestDetailDetailedResponseDto toDetailedDto(HarvestDetail harvestDetail);

}