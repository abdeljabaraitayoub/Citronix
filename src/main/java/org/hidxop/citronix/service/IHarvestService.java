package org.hidxop.citronix.service;

import org.hidxop.citronix.dto.harvest.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IHarvestService extends IService<HarvestBasicResponseDto, HarvestDetailedResponseDto, HarvestCreateRequestDto, HarvestUpdateRequestDto,UUID> {


    @Transactional
    HarvestBasicResponseDto save(HarvestDetailsCreateRequestDto harvestCreateRequestDto);
}
