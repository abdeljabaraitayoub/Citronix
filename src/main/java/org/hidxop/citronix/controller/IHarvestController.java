package org.hidxop.citronix.controller;

import org.hidxop.citronix.dto.harvest.*;
import org.hidxop.citronix.dto.tree.TreeBasicResponseDto;
import org.hidxop.citronix.dto.tree.TreeCreateRequestDto;
import org.hidxop.citronix.dto.tree.TreeDetailedResponseDto;
import org.hidxop.citronix.dto.tree.TreeUpdateRequestDto;

import java.util.UUID;

public interface IHarvestController extends IController<HarvestBasicResponseDto, HarvestDetailedResponseDto, HarvestDetailsCreateRequestDto, HarvestUpdateRequestDto, UUID>{
}
