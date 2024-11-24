package org.hidxop.citronix.dto.sale;

import jakarta.validation.constraints.Positive;
import java.util.UUID;


public record SaleUpdateRequestDto(@Positive Double unitPrice, @Positive Double quantity, String client,
                                   UUID harvestId)  {
}