package org.hidxop.citronix.dto.tree;

import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;
import java.util.UUID;

public record TreeUpdateRequestDto(UUID fieldId, @Past LocalDateTime plantedAt)  {
}