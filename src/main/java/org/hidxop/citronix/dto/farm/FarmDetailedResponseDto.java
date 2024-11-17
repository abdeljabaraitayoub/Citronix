package org.hidxop.citronix.dto.farm;

import org.hidxop.citronix.dto.field.FieldBasicResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public record FarmDetailedResponseDto(UUID id,
                                      String name,
                                      String location,
                                      double totalArea,
                                      List<FieldBasicResponseDto> fields,
                                      LocalDateTime createdAt) {
}