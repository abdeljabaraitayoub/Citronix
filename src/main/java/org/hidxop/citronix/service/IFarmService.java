package org.hidxop.citronix.service;

import org.hidxop.citronix.dto.farm.FarmBasicResponseDto;
import org.hidxop.citronix.dto.farm.FarmCreateRequestDto;
import org.hidxop.citronix.dto.farm.FarmUpdateRequestDto;

import java.util.UUID;

public interface IFarmService extends IService<FarmBasicResponseDto, FarmCreateRequestDto, FarmUpdateRequestDto, UUID> {
}
