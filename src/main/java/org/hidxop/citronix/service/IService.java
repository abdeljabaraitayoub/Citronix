package org.hidxop.citronix.service;

import java.util.List;

public interface IService<BasicDto,DetailedDto ,CreateDto, UpdateDto, ID> {

    List<BasicDto> findAll();


    DetailedDto findById(ID id);


    DetailedDto save(CreateDto createDto);


    void deleteById(ID id);


    boolean existsById(ID id);

    DetailedDto update(ID id, UpdateDto updateDto);
}