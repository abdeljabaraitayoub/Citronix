package org.hidxop.citronix.dto.tree;

import org.hidxop.citronix.domain.enumeration.ProductivityStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TreeBasicResponseDto(UUID id, int age, LocalDateTime plantedAt, ProductivityStatus productivityStatus,
                                   double seasonalProductivity) {
}