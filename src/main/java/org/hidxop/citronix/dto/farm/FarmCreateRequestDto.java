package org.hidxop.citronix.dto.farm;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record FarmCreateRequestDto(
        @NotBlank String name,
        @NotBlank String location,
        @Positive @Min(2000) double totalArea
)  {
}