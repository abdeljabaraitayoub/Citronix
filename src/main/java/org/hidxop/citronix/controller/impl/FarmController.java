package org.hidxop.citronix.controller.impl;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.controller.IFarmController;
import org.hidxop.citronix.dto.farm.FarmBasicResponseDto;
import org.hidxop.citronix.dto.farm.FarmCreateRequestDto;
import org.hidxop.citronix.dto.farm.FarmDetailedResponseDto;
import org.hidxop.citronix.dto.farm.FarmUpdateRequestDto;
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
public class FarmController implements IFarmController {
    private final IFarmService farmService;

    @Override
    @GetMapping
    public ResponseEntity<List<FarmBasicResponseDto>> findAll(){
        List<FarmBasicResponseDto> farms=farmService.findAll();
        return ResponseEntity.ok(farms);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FarmDetailedResponseDto> findById(@PathVariable @Valid UUID id){
        FarmDetailedResponseDto farms=farmService.findById(id);
        return ResponseEntity.ok(farms);
    }

    @Override
    @PostMapping()
    public ResponseEntity<FarmBasicResponseDto> save(@RequestBody @Valid FarmCreateRequestDto requestDto){
        FarmBasicResponseDto detailedResponseDto =farmService.save(requestDto);
        return new ResponseEntity<>(detailedResponseDto, HttpStatus.CREATED);
    }
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Valid UUID id){
        farmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<FarmBasicResponseDto> update(@PathVariable @Valid UUID id, @RequestBody @Valid FarmUpdateRequestDto requestDto){
        FarmBasicResponseDto updatedFarm = farmService.update(id, requestDto);
        return ResponseEntity.ok(updatedFarm);
    }

}
