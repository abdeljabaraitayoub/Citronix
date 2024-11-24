package org.hidxop.citronix.dto.sale;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record SaleCreateRequestDto(@Positive double unitPrice, @Positive double quantity, @NotNull String client,
                                   UUID harvestId) {
}