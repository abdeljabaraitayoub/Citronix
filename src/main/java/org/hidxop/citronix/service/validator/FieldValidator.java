package org.hidxop.citronix.service.validator;

import lombok.RequiredArgsConstructor;
import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.dto.farm.FarmMapper;
import org.hidxop.citronix.dto.field.FieldUpdateRequestDto;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.hidxop.citronix.service.impl.FarmService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FieldValidator {
    private final FarmService farmService;
    private static final int MAX_FIELDS_PER_FARM = 10;
    private static final double MAX_FIELD_TO_FARM_RATIO = 0.5;
    private final FarmMapper farmMapper;

    public void validateFieldCreation(Field field) {
        Farm farm = field.getFarm();
        validateFieldCount(farm);
        validateFieldArea(field.getArea(), farm);
        validateFarmFreeArea(field.getArea(), farm, 0);
    }

    public void validateFieldUpdate(Field existingField, FieldUpdateRequestDto request) {
        UUID newFarmId = request.farmId();
        double newArea = request.area() != null ? request.area() : existingField.getArea();

        if (newFarmId != null && !existingField.getFarm().getId().equals(newFarmId)) {
            Farm newFarm = farmMapper.toEntity(farmService.findById(newFarmId));
            validateFieldCount(newFarm);
            validateFieldArea(newArea, newFarm);
            validateFarmFreeArea(newArea, newFarm, 0);
        } else {
            Farm currentFarm = existingField.getFarm();
            validateFieldArea(newArea, currentFarm);
            validateFarmFreeArea(newArea, currentFarm, existingField.getArea());
        }
    }

    private void validateFieldCount(Farm farm) {
        if (farmService.countFieldsPerFarm(farm) >= MAX_FIELDS_PER_FARM) {
            throw new InvalidStateException("The fields number shouldn't be bigger than 10 for each farm.");
        }
    }

    private void validateFieldArea(double fieldArea, Farm farm) {
        if (fieldArea > farm.getTotalArea() * MAX_FIELD_TO_FARM_RATIO) {
            throw new InvalidStateException("The field area shouldn't be bigger than 50% of the farm area.");
        }
    }

    private void validateFarmFreeArea(double newArea, Farm farm, double existingArea) {
        double freeArea = farmService.calculateFreeArea(farm) + existingArea;
        if (freeArea < newArea) {
            throw new InvalidStateException("The farm free Area should be bigger than the new Field Area.");
        }
    }
}