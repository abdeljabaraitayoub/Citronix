package org.hidxop.citronix.dto.harvest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.UUID;

public record HarvestDetailsCreateRequestDto(@NotNull UUID fieldId,
                                         @Past LocalDateTime harvestDate)  {
}