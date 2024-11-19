package org.hidxop.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.dto.field.*;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
import org.hidxop.citronix.repository.FieldRepository;
import org.hidxop.citronix.service.IFieldService;
import org.hidxop.citronix.service.validator.FieldValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
public class FieldService implements IFieldService {

    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final FieldValidator fieldValidator;

    private final FarmService farmService;



    public Field getFieldById(UUID id){
        return fieldRepository.findById(id).orElseThrow(() -> new NotFoundException("Field Not Found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FieldBasicResponseDto> findAll() {
        return fieldMapper.toBasicDto(fieldRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public FieldDetailedResponseDto findById(UUID uuid) {
        return fieldMapper.toDetailedDto(getFieldById(uuid));
    }

    @Override
    @Transactional
    public FieldBasicResponseDto save(FieldCreateRequestDto request) {
        Farm farm =farmService.getFarmById(request.farmId());
        Field field = fieldMapper.toEntity(request);
        field.setFarm(farm);
        fieldValidator.validateFieldCreation(field);
        return fieldMapper.toBasicDto(fieldRepository.save(field));
    }

    @Override
    @Transactional
    public FieldBasicResponseDto update(UUID uuid, FieldUpdateRequestDto request) {
        Field existingField = getFieldById(uuid);

        if (request.area() != null || request.farmId() != null) {
            fieldValidator.validateFieldUpdate(existingField, request);
        }

        if (request.farmId() != null) {
            Farm newFarm = farmService.getFarmById(request.farmId());
            existingField.setFarm(newFarm);
        }

        fieldMapper.partialUpdate(request, existingField);
        return fieldMapper.toBasicDto(fieldRepository.save(existingField));
    }

    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        fieldRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Field Not Found"));
        fieldRepository.deleteById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public double calculateTreePerAreaRate(UUID uuid) {
        Field field= getFieldById(uuid);
        return 1000.0 * field.getTrees().size() / field.getArea();
    }
}
