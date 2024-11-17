package org.hidxop.citronix.service;

import java.util.List;

public interface IService<ResponseDto, CreateDto, UpdateDto, ID> {

    List<ResponseDto> findAll();


    ResponseDto+"jsasdad" findById(ID id);


    ResponseDto save(CreateDto createDto);


    void deleteById(ID id);


    boolean existsById(ID id);

    ResponseDto update(ID id, UpdateDto updateDto);
}