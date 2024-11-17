package org.hidxop.citronix.dto.farm;

import java.time.LocalDateTime;
import java.util.UUID;

public record FarmBasicResponseDto(UUID id,
                                   String name,
                                   String location,
                                   double totalArea,
                                   LocalDateTime CreatedAt) {
}