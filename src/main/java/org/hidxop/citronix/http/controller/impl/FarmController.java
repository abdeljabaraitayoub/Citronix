package org.hidxop.citronix.http.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.http.controller.IFarmController;
import org.hidxop.citronix.dto.farm.FarmBasicResponseDto;
import org.hidxop.citronix.dto.farm.FarmCreateRequestDto;
import org.hidxop.citronix.dto.farm.FarmDetailedResponseDto;
import org.hidxop.citronix.dto.farm.FarmUpdateRequestDto;
import org.hidxop.citronix.http.viewModels.FarmBasicResponseVm;
import org.hidxop.citronix.service.IFarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("v1/farm")
public class FarmController {
    private final IFarmService farmService;

    @GetMapping
    public ResponseEntity<List<FarmBasicResponseVm>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String totalArea
    ){
        List<FarmBasicResponseDto> farms = farmService.search(name, location, totalArea);
        return ResponseEntity.ok(FarmBasicResponseVm.toVm(farms));
    }


    @GetMapping("/{id}")
    public ResponseEntity<FarmDetailedResponseDto> findById(@PathVariable @Valid UUID id){
        FarmDetailedResponseDto farm = farmService.findById(id);
        return ResponseEntity.ok(farm);
    }

    @PostMapping
    public ResponseEntity<FarmBasicResponseDto> save(@RequestBody @Valid FarmCreateRequestDto requestDto){
        FarmBasicResponseDto response = farmService.save(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Valid UUID id){
        farmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmBasicResponseDto> update(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid FarmUpdateRequestDto requestDto
    ){
        FarmBasicResponseDto updatedFarm = farmService.update(id, requestDto);
        return ResponseEntity.ok(updatedFarm);
    }
}