package org.hidxop.citronix.http.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IController<BasicDto, DetailedDto, CreateDto, UpdateDto, ID> {

    @GetMapping
    ResponseEntity<List<BasicDto>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<DetailedDto> findById(@PathVariable ID id);

    @PostMapping
    ResponseEntity<BasicDto> save(@RequestBody CreateDto createDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable ID id);

    @PutMapping("/{id}")
    ResponseEntity<BasicDto> update(@PathVariable ID id, @RequestBody UpdateDto updateDto);
}