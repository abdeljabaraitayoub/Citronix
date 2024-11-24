package org.hidxop.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.domain.entitiy.Harvest;
import org.hidxop.citronix.domain.entitiy.HarvestDetail;
import org.hidxop.citronix.dto.harvest.*;
import org.hidxop.citronix.dto.harvestDetail.HarvestDetailMapper;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
import org.hidxop.citronix.repository.HarvestDetailRepository;
import org.hidxop.citronix.repository.HarvestRepository;
import org.hidxop.citronix.service.IHarvestService;
import org.hidxop.citronix.service.validator.HarvestValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HarvestService implements IHarvestService {

    private final HarvestMapper harvestMapper;
    private final HarvestRepository harvestRepository;
    private final HarvestValidator harvestValidator;
    private final HarvestDetailMapper harvestDetailMapper;
    private final FieldService fieldService;
    private final HarvestDetailRepository harvestDetailRepository;

    @Override
    public List<HarvestBasicResponseDto> findAll() {
        return harvestMapper.toBasicDto(getAllHarvests());
    }

    @Override
    public HarvestDetailedResponseDto findById(UUID uuid) {
        Harvest harvest=getHarvestById(uuid);
        return harvestMapper.toDetailedDto(harvest);
    }

    @Transactional
    public HarvestBasicResponseDto save(HarvestCreateRequestDto harvestCreateRequestDto) {
        Harvest harvest =harvestMapper.toEntity(harvestCreateRequestDto);
//        harvestValidator.validateCreateHarvest(fiel,getAllHarvests());
        harvest=harvestRepository.save(harvest);
        return harvestMapper.toBasicDto(harvest);
    }


    @Override
    @Transactional
    public HarvestBasicResponseDto save(HarvestDetailsCreateRequestDto harvestCreateRequestDto) {
        HarvestDetail harvestDetail = harvestDetailMapper.toEntity(harvestCreateRequestDto);
        Harvest harvest = Harvest.builder().harvestDate(harvestDetail.getHarvest().getHarvestDate()).build();
        Field field = fieldService.getFieldById(harvestDetail.getTree().getField().getId());

         harvestValidator.validateCreateHarvest(field,getAllHarvests(),harvestDetail.getHarvest().getHarvestDate());

        Harvest finalHarvest = harvest;
        List<HarvestDetail> harvestDetails = new ArrayList<>();
        field.getTrees().forEach(tree -> {
            HarvestDetail newHarvestDetail = new HarvestDetail();
            newHarvestDetail.setTree(tree);
            newHarvestDetail.setHarvest(finalHarvest);
            newHarvestDetail.setQuantity(tree.getSeasonalProductivity());
            harvestDetails.add(newHarvestDetail);
        });

        finalHarvest.setHarvestDetails(harvestDetails);
        harvest = harvestRepository.save(finalHarvest);
        harvestDetailRepository.saveAll(harvestDetails);

        return harvestMapper.toBasicDto(getHarvestById(harvest.getId()));
    }
    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        Harvest harvest=getHarvestById(uuid);
        harvestRepository.delete(harvest);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return harvestRepository.existsById(uuid);
    }

    @Override
    @Transactional
    public HarvestBasicResponseDto update(UUID uuid, HarvestUpdateRequestDto harvestUpdateRequestDto) {
        Harvest existHarvest = getHarvestById(uuid);
        harvestMapper.partialUpdate(harvestUpdateRequestDto, existHarvest);
        harvestValidator.validateUpdateHarvest(
                existHarvest.getHarvestDetails().get(0).getTree().getField(),
                getAllHarvests(),
                existHarvest.getHarvestDate(),
                existHarvest
        );
        harvestRepository.save(existHarvest);
        return harvestMapper.toBasicDto(existHarvest);
    }

    public Harvest getHarvestById(UUID id){
        return harvestRepository.findById(id).orElseThrow(()->new NotFoundException("Harvest Not Found"));
    }

    public List<Harvest> getAllHarvests(){
        return harvestRepository.findAll();
    }

}
