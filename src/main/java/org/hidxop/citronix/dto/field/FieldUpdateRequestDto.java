package org.hidxop.citronix.dto.field;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.UUID;


public record FieldUpdateRequestDto(@Min(1000) double area,UUID farmId){
}