package org.hidxop.citronix.service;

import org.hidxop.citronix.dto.farm.FarmBasicResponseDto;
import org.hidxop.citronix.dto.farm.FarmCreateRequestDto;
import org.hidxop.citronix.dto.farm.FarmDetailedResponseDto;
import org.hidxop.citronix.dto.farm.FarmUpdateRequestDto;

import java.util.List;
import java.util.UUID;

public interface IFarmService extends IService<FarmBasicResponseDto, FarmDetailedResponseDto, FarmCreateRequestDto, FarmUpdateRequestDto, UUID> {
    List<FarmBasicResponseDto> search(
            String name,
            String location,
            String totalArea
    );
}
