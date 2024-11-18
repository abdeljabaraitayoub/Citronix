package org.hidxop.citronix.controller;

import org.hidxop.citronix.dto.farm.FarmBasicResponseDto;
import org.hidxop.citronix.dto.farm.FarmCreateRequestDto;
import org.hidxop.citronix.dto.farm.FarmDetailedResponseDto;
import org.hidxop.citronix.dto.farm.FarmUpdateRequestDto;

import java.util.UUID;

public interface IFarmController extends IController<FarmBasicResponseDto, FarmDetailedResponseDto, FarmCreateRequestDto, FarmUpdateRequestDto, UUID>{
}
