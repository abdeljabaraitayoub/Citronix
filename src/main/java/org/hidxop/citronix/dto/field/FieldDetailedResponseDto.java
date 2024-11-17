package org.hidxop.citronix.dto.field;

import org.hidxop.citronix.dto.farm.FarmBasicResponseDto;
import org.hidxop.citronix.dto.tree.TreeBasicResponseDto;
import java.util.List;
import java.util.UUID;

public record FieldDetailedResponseDto(UUID id, double area, FarmBasicResponseDto farm,
                                       List<TreeBasicResponseDto> trees)  {
}