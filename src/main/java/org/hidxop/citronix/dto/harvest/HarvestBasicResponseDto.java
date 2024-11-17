package org.hidxop.citronix.dto.harvest;

import org.hidxop.citronix.domain.enumeration.Season;

import java.time.LocalDateTime;
import java.util.UUID;

public record HarvestBasicResponseDto(UUID id, LocalDateTime harvestDate, Season season,
                                      double totalQuantity)  {
}