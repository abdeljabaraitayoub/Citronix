package org.hidxop.citronix.service;

import org.hidxop.citronix.dto.field.FieldBasicResponseDto;
import org.hidxop.citronix.dto.field.FieldCreateRequestDto;
import org.hidxop.citronix.dto.field.FieldDetailedResponseDto;
import org.hidxop.citronix.dto.field.FieldUpdateRequestDto;

import java.util.UUID;

public interface IFieldService extends IService<FieldBasicResponseDto, FieldDetailedResponseDto, FieldCreateRequestDto, FieldUpdateRequestDto, UUID>{
}
