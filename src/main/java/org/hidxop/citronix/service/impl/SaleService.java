package org.hidxop.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Harvest;
import org.hidxop.citronix.domain.entitiy.Sale;
import org.hidxop.citronix.dto.sale.*;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
import org.hidxop.citronix.repository.SaleRepository;
import org.hidxop.citronix.service.ISaleService;
import org.hidxop.citronix.service.validator.SaleValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class SaleService implements ISaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final HarvestService harvestService;
    private final SaleValidator saleValidator;


    public Sale getSaleById(UUID id){
        return saleRepository.findById(id).orElseThrow(()->new NotFoundException("Sale Not Found."));
    }

    @Override
    public List<SaleBasicResponseDto> findAll() {
        List<Sale> sales=saleRepository.findAll();
        return saleMapper.toBasicDto(sales);
    }

    @Override
    public SaleDetailedResponseDto findById(UUID uuid) {

        return saleMapper.toDetailedDto(getSaleById(uuid));
    }

    @Override
    @Transactional
    public SaleBasicResponseDto save(SaleCreateRequestDto saleCreateRequestDto) {
        Sale sale=saleMapper.toEntity(saleCreateRequestDto);
        Harvest harvest=harvestService.getHarvestById(sale.getHarvest().getId());
        sale.setHarvest(harvest);
        saleValidator.SaleCreateValidation(sale);
        sale=saleRepository.save(sale);

        return saleMapper.toBasicDto(sale);

    }

    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        Sale sale=getSaleById(uuid);
        saleRepository.delete(sale);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return saleRepository.existsById(uuid);
    }

    @Override
    @Transactional
    public SaleBasicResponseDto update(UUID uuid, SaleUpdateRequestDto saleUpdateRequestDto) {
        Sale sale = getSaleById(uuid);

        if (saleUpdateRequestDto.harvestId() != null &&
                !saleUpdateRequestDto.harvestId().equals(sale.getHarvest().getId())) {

            Harvest newHarvest = harvestService.getHarvestById(saleUpdateRequestDto.harvestId());
            sale.setHarvest(newHarvest);
        }

        saleMapper.partialUpdate(saleUpdateRequestDto, sale);

        saleValidator.SaleUpdateValidation(sale);

        sale = saleRepository.save(sale);
        return saleMapper.toBasicDto(sale);
    }
}
