package org.hidxop.citronix.repository;

import org.hidxop.citronix.domain.entitiy.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID>{
}