package org.hidxop.citronix.repository;

import org.hidxop.citronix.domain.entitiy.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, UUID>, JpaSpecificationExecutor<HarvestDetail> {
}