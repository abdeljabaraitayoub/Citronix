package org.hidxop.citronix.dto.tree;

import org.hidxop.citronix.domain.entitiy.Tree;
import org.hidxop.citronix.dto.field.FieldMapper;
import org.hidxop.citronix.dto.harvestDetail.HarvestDetailMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {FieldMapper.class, HarvestDetailMapper.class})
public interface TreeMapper {
    TreeBasicResponseDto toBasicDto(Tree tree);
    TreeDetailedResponseDto toDetailedDto(Tree tree);

    @AfterMapping
    default void linkHarvestDetail(@MappingTarget Tree tree) {
        tree.getHarvestDetail().forEach(harvestDetail -> harvestDetail.setTree(tree));
    }
}