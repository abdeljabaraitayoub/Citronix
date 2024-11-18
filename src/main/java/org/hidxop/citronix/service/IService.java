package org.hidxop.citronix.service;

import java.util.List;

public interface IService<BasicDto,DetailedDto ,CreateDto, UpdateDto, ID> {

    List<BasicDto> findAll();


    DetailedDto findById(ID id);


    BasicDto save(CreateDto createDto);


    void deleteById(ID id);


    boolean existsById(ID id);

    BasicDto update(ID id, UpdateDto updateDto);
}