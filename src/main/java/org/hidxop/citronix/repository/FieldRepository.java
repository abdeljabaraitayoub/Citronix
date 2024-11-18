package org.hidxop.citronix.repository;

import jakarta.persistence.Id;
import org.hidxop.citronix.domain.entitiy.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID>, JpaSpecificationExecutor<Field> {
    @Query("Select f from Field f left join f.farm left join f.trees where f.id =:id")
    Optional<Field> findByIdWithFarmAndTrees(UUID id);

}