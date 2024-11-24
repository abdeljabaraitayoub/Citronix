package org.hidxop.citronix.http.controller;

import org.hidxop.citronix.dto.tree.TreeBasicResponseDto;
import org.hidxop.citronix.dto.tree.TreeCreateRequestDto;
import org.hidxop.citronix.dto.tree.TreeDetailedResponseDto;
import org.hidxop.citronix.dto.tree.TreeUpdateRequestDto;

import java.util.UUID;

public interface ITreeController extends IController<TreeBasicResponseDto, TreeDetailedResponseDto, TreeCreateRequestDto, TreeUpdateRequestDto, UUID>{
}
