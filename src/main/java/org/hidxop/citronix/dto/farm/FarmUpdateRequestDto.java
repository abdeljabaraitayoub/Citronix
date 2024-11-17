package org.hidxop.citronix.dto.farm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
public record FarmUpdateRequestDto(@NotBlank String name,
                                   @NotBlank String location,
                                   @Positive double totalArea) {
}