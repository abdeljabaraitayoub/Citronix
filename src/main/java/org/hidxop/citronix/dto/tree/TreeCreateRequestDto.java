package org.hidxop.citronix.dto.tree;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;
import java.util.UUID;

public record TreeCreateRequestDto(UUID fieldId, @NotNull @Past LocalDateTime plantedAt){
}