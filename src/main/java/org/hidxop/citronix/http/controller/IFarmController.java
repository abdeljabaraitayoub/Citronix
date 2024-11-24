package org.hidxop.citronix.http.controller;

import org.hidxop.citronix.dto.farm.FarmBasicResponseDto;
import org.hidxop.citronix.dto.farm.FarmCreateRequestDto;
import org.hidxop.citronix.dto.farm.FarmDetailedResponseDto;
import org.hidxop.citronix.dto.farm.FarmUpdateRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface IFarmController extends IController<FarmBasicResponseDto, FarmDetailedResponseDto, FarmCreateRequestDto, FarmUpdateRequestDto, UUID> {
    @GetMapping
    ResponseEntity<List<FarmDetailedResponseDto>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String totalArea
    );
}
