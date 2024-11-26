package org.hidxop.citronix.repository;

import org.hidxop.citronix.domain.entitiy.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, UUID>{
}