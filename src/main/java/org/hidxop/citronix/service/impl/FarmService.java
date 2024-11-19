package org.hidxop.citronix.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.dto.farm.*;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
import org.hidxop.citronix.repository.FarmRepository;
import org.hidxop.citronix.service.IFarmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FarmService implements IFarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Override
    public List<FarmBasicResponseDto> findAll() {
        List<Farm> farms=farmRepository.findAll();
        return farmMapper.toBasicDto(farms);
    }


    @Override
    public FarmDetailedResponseDto findById(UUID uuid) {
    Farm farm=farmRepository.findById(uuid).orElseThrow(()->new NotFoundException("Farm Not Found."));
    return farmMapper.toDetailedDto(farm);
    }

    @Override
    @Transactional
    public FarmBasicResponseDto save(FarmCreateRequestDto farmCreateRequestDto) {
        Farm farm=farmMapper.toEntity(farmCreateRequestDto);
        farm.setCreatedAt(LocalDateTime.now());
        farm=farmRepository.save(farm);
        return farmMapper.toBasicDto(farm);
    }
    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        Farm farm=farmRepository.findById(uuid).orElseThrow(()-> new NotFoundException("Farm Not Found."));
        farmRepository.delete(farm);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return farmRepository.existsById(uuid);
    }

    @Transactional
    @Override
    public FarmBasicResponseDto update(UUID uuid, FarmUpdateRequestDto farmUpdateRequestDto) {
        Farm existingFarm = farmRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Farm not found"));

        farmMapper.partialUpdate(farmUpdateRequestDto, existingFarm);

        Farm savedFarm = farmRepository.save(existingFarm);
        return farmMapper.toBasicDto(savedFarm);
    }

    public Double calculateFreeArea(Farm farm){
        farm=refreshFromDB(farm);
        Double totalArea=farm.getTotalArea();
        Double consumedArea = farm.getFields().stream().mapToDouble(field->field.getArea()).sum();
        return totalArea - consumedArea;
    }

    public int countFieldsPerFarm(Farm farm){
        farm=refreshFromDB(farm);
        return farm.getFields().size();
    }


    private Farm refreshFromDB(Farm farm){
        return farmRepository.findById(farm.getId()).orElseThrow(()->new NotFoundException("Farm Not found."));
    }
}
