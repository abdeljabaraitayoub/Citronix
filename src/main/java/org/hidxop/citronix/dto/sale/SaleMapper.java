package org.hidxop.citronix.dto.sale;

import org.hidxop.citronix.domain.entitiy.Sale;
import org.hidxop.citronix.dto.harvest.HarvestMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {HarvestMapper.class})
public interface SaleMapper {
    SaleBasicResponseDto toBasicDto(Sale sale);
    SaleDetailedResponseDto toDetailedDto(Sale sale);

}