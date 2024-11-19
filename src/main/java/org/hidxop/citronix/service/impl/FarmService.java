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

    public Farm getFarmById(UUID uuid) {
        return farmRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Farm not found with id: " + uuid));
    }

    @Override
    public FarmDetailedResponseDto findById(UUID uuid) {
        return farmMapper.toDetailedDto(getFarmById(uuid));
    }

    @Override
    public List<FarmBasicResponseDto> findAll() {
        return farmMapper.toBasicDto(farmRepository.findAll());
    }

    @Transactional
    @Override
    public FarmBasicResponseDto save(FarmCreateRequestDto createDto) {
        Farm farm = farmMapper.toEntity(createDto);
        farm.setCreatedAt(LocalDateTime.now());
        return farmMapper.toBasicDto(farmRepository.save(farm));
    }

    @Transactional
    @Override
    public FarmBasicResponseDto update(UUID uuid, FarmUpdateRequestDto updateDto) {
        Farm farm = getFarmById(uuid);
        farmMapper.partialUpdate(updateDto, farm);
        return farmMapper.toBasicDto(farmRepository.save(farm));
    }

    @Transactional
    @Override
    public void deleteById(UUID uuid) {
        farmRepository.delete(getFarmById(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return farmRepository.existsById(uuid);
    }

    public Double calculateFreeArea(UUID farmId) {
        Farm farm = getFarmById(farmId);
        return farm.getTotalArea() - farm.getFields().stream()
                .mapToDouble(field -> field.getArea())
                .sum();
    }

    public int countFields(UUID farmId) {
        return getFarmById(farmId).getFields().size();
    }
}