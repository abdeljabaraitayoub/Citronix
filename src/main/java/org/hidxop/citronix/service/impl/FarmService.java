package org.hidxop.citronix.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.dto.farm.*;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
import org.hidxop.citronix.repository.FarmRepository;
import org.hidxop.citronix.service.IFarmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FarmService implements IFarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;
    @PersistenceContext
    private EntityManager entityManager;
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
    @Override
    public List<FarmBasicResponseDto> search(String name, String location, String totalArea) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> farm = query.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            predicates.add(
                    cb.like(
                            cb.lower(farm.get("name")),
                            "%" + name.toLowerCase() + "%"
                    )
            );
        }

        if (location != null && !location.trim().isEmpty()) {
            predicates.add(
                    cb.like(
                            cb.lower(farm.get("location")),
                            "%" + location.toLowerCase() + "%"
                    )
            );
        }

        if (totalArea != null && !totalArea.trim().isEmpty()) {
            Double areaValue = Double.parseDouble(totalArea);
            predicates.add(cb.equal(farm.get("totalArea"), areaValue));
        }

        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        List<Farm> farms = entityManager.createQuery(query).getResultList();
        if (farms.isEmpty()){
            throw new NotFoundException("There is no Farm with this Criteria.");
        }
        return farmMapper.toBasicDto(farms);
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
        Farm farm = getFarmById(uuid);
        if (!farm.getFields().isEmpty()) {
            throw new InvalidStateException("Cannot delete farm with existing fields. Please delete or reassign fields first.");
        }
        farmRepository.delete(farm);
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