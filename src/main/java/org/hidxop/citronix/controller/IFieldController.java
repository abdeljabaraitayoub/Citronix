package org.hidxop.citronix.controller;

import org.hidxop.citronix.dto.field.FieldBasicResponseDto;
import org.hidxop.citronix.dto.field.FieldCreateRequestDto;
import org.hidxop.citronix.dto.field.FieldDetailedResponseDto;
import org.hidxop.citronix.dto.field.FieldUpdateRequestDto;

import java.util.UUID;

public interface IFieldController extends IController<FieldBasicResponseDto, FieldDetailedResponseDto, FieldCreateRequestDto, FieldUpdateRequestDto, UUID>{
}
