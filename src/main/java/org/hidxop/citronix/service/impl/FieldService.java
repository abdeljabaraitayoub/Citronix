package org.hidxop.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.dto.farm.FarmMapper;
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

    @Override
    @Transactional(readOnly = true)
    public List<FieldBasicResponseDto> findAll() {
        return fieldMapper.toBasicDto(fieldRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public FieldDetailedResponseDto findById(UUID uuid) {
        return fieldMapper.toDetailedDto(
                fieldRepository.findByIdWithFarmAndTrees(uuid)
                        .orElseThrow(() -> new NotFoundException("Field Not Found"))
        );
    }

    @Override
    @Transactional
    public FieldBasicResponseDto save(FieldCreateRequestDto request) {
        Field field = fieldMapper.toEntity(request);
        fieldValidator.validateFieldCreation(field);
        return fieldMapper.toBasicDto(fieldRepository.save(field));
    }

    @Override
    @Transactional
    public FieldBasicResponseDto update(UUID uuid, FieldUpdateRequestDto request) {
        Field existingField = fieldRepository.findByIdWithFarmAndTrees(uuid)
                .orElseThrow(() -> new NotFoundException("Field Not Found"));

        if (request.area() != null || request.farmId() != null) {
            fieldValidator.validateFieldUpdate(existingField, request);
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
    public double calculateTreePerAreaRate(Field field) {
        field = fieldRepository.findById(field.getId())
                .orElseThrow(() -> new NotFoundException("Field not found"));
        return 1000.0 * field.getTrees().size() / field.getArea();
    }
}
