package org.hidxop.citronix.dto.harvest;

import org.hidxop.citronix.domain.enumeration.Season;
import org.hidxop.citronix.dto.harvestDetail.HarvestDetailBasicResponseDto;
import org.hidxop.citronix.dto.sale.SaleBasicResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record HarvestDetailedResponseDto(UUID id, LocalDateTime harvestDate, Season season, double totalQuantity,
                                         List<HarvestDetailBasicResponseDto> harvestDetails,
                                         List<SaleBasicResponseDto> sale) {
}