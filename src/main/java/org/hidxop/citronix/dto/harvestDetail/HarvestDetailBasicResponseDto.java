package org.hidxop.citronix.dto.harvestDetail;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link org.hidxop.citronix.domain.entitiy.HarvestDetail}
 */
public record HarvestDetailBasicResponseDto(UUID id, double quantity) implements Serializable {
}