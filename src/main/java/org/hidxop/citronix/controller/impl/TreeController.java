package org.hidxop.citronix.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.controller.ITreeController;
import org.hidxop.citronix.dto.tree.TreeBasicResponseDto;
import org.hidxop.citronix.dto.tree.TreeCreateRequestDto;
import org.hidxop.citronix.dto.tree.TreeDetailedResponseDto;
import org.hidxop.citronix.dto.tree.TreeUpdateRequestDto;
import org.hidxop.citronix.service.ITreeService;
import org.hidxop.citronix.service.impl.TreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("v1/tree")
public class TreeController implements ITreeController {


    private final ITreeService treeService;

    @Override
    @GetMapping
    public ResponseEntity<List<TreeBasicResponseDto>> findAll() {
        List<TreeBasicResponseDto> treeBasicResponses =treeService.findAll();
        return ResponseEntity.ok(treeBasicResponses);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TreeDetailedResponseDto> findById(@Valid @PathVariable UUID id) {
        TreeDetailedResponseDto detailedResponseDto=treeService.findById(id);
        return ResponseEntity.ok(detailedResponseDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<TreeBasicResponseDto> save(TreeCreateRequestDto treeCreateRequestDto) {
        TreeBasicResponseDto treeBasicResponse=treeService.save(treeCreateRequestDto);
        return new ResponseEntity<>(treeBasicResponse, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable UUID id) {
        treeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TreeBasicResponseDto> update(@Valid @PathVariable UUID id,@Valid @RequestBody TreeUpdateRequestDto treeUpdateRequestDto) {
        TreeBasicResponseDto treeBasicResponseDto=treeService.update(id,treeUpdateRequestDto);
        return ResponseEntity.ok(treeBasicResponseDto);
    }
}
