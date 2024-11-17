package org.hidxop.citronix.dto.sale;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record SaleBasicResponseDto(UUID id, LocalDateTime saleDate, double unitPrice, double quantity,
                                   String client)  {
}