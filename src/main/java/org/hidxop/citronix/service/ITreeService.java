package org.hidxop.citronix.service;

import org.hidxop.citronix.dto.tree.TreeBasicResponseDto;
import org.hidxop.citronix.dto.tree.TreeCreateRequestDto;
import org.hidxop.citronix.dto.tree.TreeDetailedResponseDto;
import org.hidxop.citronix.dto.tree.TreeUpdateRequestDto;

import java.util.UUID;

public interface ITreeService extends IService<TreeBasicResponseDto, TreeDetailedResponseDto, TreeCreateRequestDto, TreeUpdateRequestDto, UUID> {
}
