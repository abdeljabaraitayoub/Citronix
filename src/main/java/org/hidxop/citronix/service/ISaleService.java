package org.hidxop.citronix.service;

import org.hidxop.citronix.dto.sale.SaleBasicResponseDto;
import org.hidxop.citronix.dto.sale.SaleCreateRequestDto;
import org.hidxop.citronix.dto.sale.SaleDetailedResponseDto;
import org.hidxop.citronix.dto.sale.SaleUpdateRequestDto;
import org.hidxop.citronix.dto.tree.TreeBasicResponseDto;
import org.hidxop.citronix.dto.tree.TreeCreateRequestDto;
import org.hidxop.citronix.dto.tree.TreeDetailedResponseDto;
import org.hidxop.citronix.dto.tree.TreeUpdateRequestDto;

import java.util.UUID;

public interface ISaleService extends IService<SaleBasicResponseDto, SaleDetailedResponseDto, SaleCreateRequestDto, SaleUpdateRequestDto, UUID> {
}
