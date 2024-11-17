package org.hidxop.citronix.dto.tree;

import org.hidxop.citronix.domain.enumeration.ProductivityStatus;
import org.hidxop.citronix.dto.field.FieldBasicResponseDto;
import org.hidxop.citronix.dto.harvestDetail.HarvestDetailBasicResponseDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TreeDetailedResponseDto(UUID id, int age, FieldBasicResponseDto field, LocalDateTime plantedAt,
                                      ProductivityStatus productivityStatus, double seasonalProductivity,
                                      List<HarvestDetailBasicResponseDto> harvestDetail) {
}