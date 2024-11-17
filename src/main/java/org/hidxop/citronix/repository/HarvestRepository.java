package org.hidxop.citronix.repository;

import org.hidxop.citronix.domain.entitiy.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface HarvestRepository extends JpaRepository<Harvest, UUID>, JpaSpecificationExecutor<Harvest> {
}