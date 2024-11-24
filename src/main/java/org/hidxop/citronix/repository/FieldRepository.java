package org.hidxop.citronix.repository;

import org.hidxop.citronix.domain.entitiy.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FieldRepository extends JpaRepository<Field, UUID>, JpaSpecificationExecutor<Field> {
    @Query("SELECT DISTINCT f FROM Field f " +
            "LEFT JOIN FETCH f.farm " +
            "RIGHT JOIN FETCH f.trees " +
            "WHERE f.id = :id")
    Optional<Field> findByIdWithFarmAndTrees(@Param("id") UUID id);
}