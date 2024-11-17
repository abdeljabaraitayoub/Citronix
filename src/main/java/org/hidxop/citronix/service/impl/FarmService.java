package org.hidxop.citronix.service.impl;

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
    public FarmDetailedResponseDto save(FarmCreateRequestDto farmCreateRequestDto) {
        Farm farm=farmMapper.toEntity(farmCreateRequestDto);
        farm.setCreatedAt(LocalDateTime.now());
        farm=farmRepository.save(farm);
        return farmMapper.toDetailedDto(farm);
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
    public FarmDetailedResponseDto update(UUID uuid, FarmUpdateRequestDto farmUpdateRequestDto) {
        Farm existingFarm = farmRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Farm not found"));

        Farm newFarm=farmMapper.toEntity(farmUpdateRequestDto);

        existingFarm.setName(newFarm.getName());
        existingFarm.setLocation(newFarm.getLocation());
        existingFarm.setTotalArea(newFarm.getTotalArea());

        Farm savedFarm = farmRepository.save(existingFarm);
        return farmMapper.toDetailedDto(savedFarm);
    }
}
