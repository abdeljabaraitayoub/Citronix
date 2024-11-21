package org.hidxop.citronix.dto.harvest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.time.LocalDateTime;


public record HarvestCreateRequestDto(@NotBlank @Past LocalDateTime harvestDate)  {
}