package org.hidxop.citronix.dto.farm;
import jakarta.validation.constraints.Positive;

public record FarmUpdateRequestDto(
        String name,
        String location,
        @Positive
        Double totalArea
) {
}
