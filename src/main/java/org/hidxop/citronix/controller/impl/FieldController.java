package org.hidxop.citronix.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.controller.IFieldController;
import org.hidxop.citronix.dto.field.FieldBasicResponseDto;
import org.hidxop.citronix.dto.field.FieldCreateRequestDto;
import org.hidxop.citronix.dto.field.FieldDetailedResponseDto;
import org.hidxop.citronix.dto.field.FieldUpdateRequestDto;
import org.hidxop.citronix.service.IFieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("v1/field")
public class FieldController implements IFieldController {
    private final IFieldService fieldService;


    @Override
    @GetMapping
    public ResponseEntity<List<FieldBasicResponseDto>> findAll() {
        List<FieldBasicResponseDto> fields = fieldService.findAll();
        return ResponseEntity.ok(fields);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FieldDetailedResponseDto> findById(@PathVariable UUID id) {
        FieldDetailedResponseDto detailedResponseDto= fieldService.findById(id);
        return ResponseEntity.ok(detailedResponseDto);
    }


    @Override
    @PostMapping
    public ResponseEntity<FieldBasicResponseDto> save(@Valid @RequestBody FieldCreateRequestDto fieldCreateRequestDto) {
        FieldBasicResponseDto detailedResponseDto =fieldService.save(fieldCreateRequestDto);
        return new ResponseEntity<>(detailedResponseDto, HttpStatus.CREATED);
    }



    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable UUID id) {
        fieldService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<FieldBasicResponseDto> update(@Valid @PathVariable UUID id,@Valid @RequestBody FieldUpdateRequestDto fieldUpdateRequestDto) {
        FieldBasicResponseDto detailedResponseDto =fieldService.update(id,fieldUpdateRequestDto);
        return new ResponseEntity<>(detailedResponseDto, HttpStatus.OK);
    }
}
