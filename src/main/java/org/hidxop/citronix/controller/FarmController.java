package org.hidxop.citronix.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class FarmController {
    private final IFarmService farmService;

    @GetMapping
    public ResponseEntity<List<FarmBasicResponseDto>> findAll(){
        List<FarmBasicResponseDto> farms=farmService.findAll();
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDetailedResponseDto> findById(@PathVariable @Valid String id){
        FarmDetailedResponseDto farms=farmService.findById(UUID.fromString(id));
        return ResponseEntity.ok(farms);
    }
    @PostMapping()
    public ResponseEntity<FarmDetailedResponseDto> save(@RequestBody @Valid FarmCreateRequestDto requestDto){
        FarmDetailedResponseDto detailedResponseDto =farmService.save(requestDto);
        return new ResponseEntity<>(detailedResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable @Valid UUID id){
        farmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmDetailedResponseDto> update(@PathVariable @Valid UUID id, @RequestBody @Valid FarmUpdateRequestDto requestDto){
        FarmDetailedResponseDto updatedFarm = farmService.update(id, requestDto);
        return ResponseEntity.ok(updatedFarm);
    }

}
