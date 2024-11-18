package org.hidxop.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.dto.field.*;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
import org.hidxop.citronix.repository.FieldRepository;
import org.hidxop.citronix.service.IFieldService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FieldService implements IFieldService {
    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final FarmService farmService;

    @Override
    public List<FieldBasicResponseDto> findAll() {
        List<Field> fields= fieldRepository.findAll();
        return fieldMapper.toBasicDto(fields);
    }

    @Override
    public FieldDetailedResponseDto findById(UUID uuid) {
        Field field=fieldRepository.findByIdWithFarmAndTrees(uuid).orElseThrow(()->new NotFoundException("Field Not Found."));
        return fieldMapper.toDetailedDto(field);
    }

    @Override
    @Transactional
    public FieldBasicResponseDto save(FieldCreateRequestDto fieldCreateRequestDto) {
        Field field =fieldMapper.toEntity(fieldCreateRequestDto);
        fieldCreationValidation(field);
        field=fieldRepository.save(field);
        return fieldMapper.toBasicDto(field);
    }


    private void fieldCreationValidation(Field field){
        Double freeArea=farmService.calculateFreeArea(field.getFarm());
        double farmArea= farmService.findById(field.getFarm().getId()).totalArea();
        int fieldCount=farmService.countFieldsPerFarm(field.getFarm());
        if (fieldCount>9){
            throw new InvalidStateException("The fields number shouldn't be bigger than 10 for each farm.");
        }
        if (field.getArea() > farmArea * 0.5) {
            throw new InvalidStateException("The field area shouldn't be bigger than 50% of the farm area.");
        }
        if (freeArea<field.getArea()){
            throw new InvalidStateException("The farm free Area should be bigger than the the new Field Area.");
        }
    }

    @Transactional
    @Override
    public void deleteById(UUID uuid) {
        Field field=fieldRepository.findById(uuid).orElseThrow(()->new NotFoundException("Field Not Found."));
        fieldRepository.delete(field);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return fieldRepository.existsById(uuid);
    }
    @Transactional
    @Override
    public FieldBasicResponseDto update(UUID uuid, FieldUpdateRequestDto fieldUpdateRequestDto) {
        Field existfield=fieldRepository.findByIdWithFarmAndTrees(uuid).orElseThrow(()->new NotFoundException("Field Not Found."));
        Field newfield=fieldMapper.toEntity(fieldUpdateRequestDto);
        fieldUpdateValidation(existfield,newfield);
        if (newfield.getArea() != 0.0) {
            existfield.setArea(newfield.getArea());
        }
        if (newfield.getFarm() != null) {
            existfield.setFarm(newfield.getFarm());
        }

        existfield=fieldRepository.save(existfield);
        return fieldMapper.toBasicDto(existfield);
    }



    private void fieldUpdateValidation(Field existfield,Field newField){
        double freeArea;
        double farmArea;
        int fieldCount;
        if (!existfield.getFarm().getId().equals(newField.getFarm().getId())){
            freeArea=farmService.calculateFreeArea(newField.getFarm());
            farmArea= farmService.findById(newField.getFarm().getId()).totalArea();
            fieldCount=farmService.countFieldsPerFarm(newField.getFarm());

            if (fieldCount>9){
                throw new InvalidStateException("The fields number shouldn't be bigger than 10 for each farm.");
            }
            if (newField.getArea() > farmArea * 0.5) {
                throw new InvalidStateException("The field area shouldn't be bigger than 50% of the farm area.");
            }
            if (freeArea<newField.getArea()){
                throw new InvalidStateException("The farm free Area should be bigger than the the new Field Area.");
            }

        }
        else {
            freeArea=farmService.calculateFreeArea(existfield.getFarm());
            farmArea= farmService.findById(existfield.getFarm().getId()).totalArea();
            if (newField.getArea() > farmArea * 0.5) {
                throw new InvalidStateException("The field area shouldn't be bigger than 50% of the farm area.");
            }
            if (freeArea+existfield.getArea()<newField.getArea()){
                throw new InvalidStateException("The farm free Area should be bigger than the the new Field Area.");
            }
        }

    }

}
