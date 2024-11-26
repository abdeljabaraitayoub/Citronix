package org.hidxop.citronix.repository;

import org.hidxop.citronix.domain.entitiy.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreeRepository extends JpaRepository<Tree, UUID> {
}