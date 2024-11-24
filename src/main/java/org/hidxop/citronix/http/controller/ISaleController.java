package org.hidxop.citronix.http.controller;


import org.hidxop.citronix.dto.sale.SaleBasicResponseDto;
import org.hidxop.citronix.dto.sale.SaleCreateRequestDto;
import org.hidxop.citronix.dto.sale.SaleDetailedResponseDto;
import org.hidxop.citronix.dto.sale.SaleUpdateRequestDto;

import java.util.UUID;

public interface ISaleController extends IController<SaleBasicResponseDto, SaleDetailedResponseDto, SaleCreateRequestDto, SaleUpdateRequestDto, UUID> {
}
