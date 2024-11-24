package org.hidxop.citronix.dto.harvest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public record HarvestUpdateRequestDto(LocalDateTime harvestDate) {
}