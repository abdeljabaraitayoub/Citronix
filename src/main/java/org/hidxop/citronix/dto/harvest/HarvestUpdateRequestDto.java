package org.hidxop.citronix.dto.harvest;

import java.io.Serializable;
import java.time.LocalDateTime;


public record HarvestUpdateRequestDto(LocalDateTime harvestDate) {
}