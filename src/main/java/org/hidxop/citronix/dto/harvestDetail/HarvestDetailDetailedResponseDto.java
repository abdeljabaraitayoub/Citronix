package org.hidxop.citronix.dto.harvestDetail;

import org.hidxop.citronix.dto.harvest.HarvestBasicResponseDto;
import org.hidxop.citronix.dto.tree.TreeBasicResponseDto;

import java.util.UUID;


public record HarvestDetailDetailedResponseDto(UUID id, TreeBasicResponseDto tree, HarvestBasicResponseDto harvest,
                                               double quantity)  {
}