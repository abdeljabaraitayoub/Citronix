package org.hidxop.citronix.dto.sale;

import org.hidxop.citronix.dto.harvest.HarvestBasicResponseDto;
import java.time.LocalDateTime;
import java.util.UUID;

public record SaleDetailedResponseDto(UUID id, LocalDateTime saleDate, double unitPrice, double quantity, String client,
                                      HarvestBasicResponseDto harvest) {
}