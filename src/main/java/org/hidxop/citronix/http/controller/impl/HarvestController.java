package org.hidxop.citronix.http.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.http.controller.IHarvestController;
import org.hidxop.citronix.dto.harvest.HarvestBasicResponseDto;
import org.hidxop.citronix.dto.harvest.HarvestDetailedResponseDto;
import org.hidxop.citronix.dto.harvest.HarvestDetailsCreateRequestDto;
import org.hidxop.citronix.dto.harvest.HarvestUpdateRequestDto;
import org.hidxop.citronix.service.IHarvestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("v1/harvest")
public class HarvestController implements IHarvestController {
    private final IHarvestService harvestService;



    @Override
    @GetMapping
    public ResponseEntity<List<HarvestBasicResponseDto>> findAll() {
        List<HarvestBasicResponseDto> basicResponses=harvestService.findAll();
        return ResponseEntity.ok(basicResponses);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<HarvestDetailedResponseDto> findById(@Valid @PathVariable UUID id) {

        HarvestDetailedResponseDto detailedResponseDto =harvestService.findById(id);
        return ResponseEntity.ok(detailedResponseDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<HarvestBasicResponseDto> save(@Valid @RequestBody HarvestDetailsCreateRequestDto harvestDetailsCreateRequestDto) {
        return new ResponseEntity<>(harvestService.save(harvestDetailsCreateRequestDto), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable UUID id) {
        harvestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<HarvestBasicResponseDto> update(@Valid @PathVariable UUID id,@RequestBody HarvestUpdateRequestDto harvestUpdateRequestDto) {
        HarvestBasicResponseDto harvestBasicResponseDto = harvestService.update(id,harvestUpdateRequestDto);
        return ResponseEntity.ok(harvestBasicResponseDto);

    }
}
