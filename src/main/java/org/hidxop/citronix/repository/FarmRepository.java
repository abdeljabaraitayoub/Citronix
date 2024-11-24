package org.hidxop.citronix.repository;

import org.hidxop.citronix.domain.entitiy.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FarmRepository extends JpaRepository<Farm, UUID> {

}