package org.hidxop.citronix.repository;

import org.hidxop.citronix.domain.entitiy.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID>, JpaSpecificationExecutor<Field> {
}