package org.hidxop.citronix.dto.tree;

import org.hidxop.citronix.domain.entitiy.Tree;
import org.hidxop.citronix.dto.field.FieldMapper;
import org.hidxop.citronix.dto.harvestDetail.HarvestDetailMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TreeMapper {
    TreeBasicResponseDto toBasicDto(Tree tree);
    TreeDetailedResponseDto toDetailedDto(Tree tree);
    List<TreeBasicResponseDto> toBasicDto(List<Tree> trees);
    List<TreeDetailedResponseDto> toDetailedDto(List<Tree> trees);

    @Mapping(source = "fieldId", target = "field.id")
    Tree toEntity(TreeCreateRequestDto treeCreateRequestDto);

    @Mapping(source = "fieldId", target = "field.id")
    Tree toEntity(TreeUpdateRequestDto treeUpdateRequestDto);

    @Mapping(source = "fieldId", target = "field.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(TreeUpdateRequestDto treeUpdateRequestDto, @MappingTarget Tree tree);
}