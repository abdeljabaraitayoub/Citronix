package org.hidxop.citronix.dto.farm;

import org.hidxop.citronix.domain.entitiy.Farm;
import org.mapstruct.*;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FarmMapper {

    FarmBasicResponseDto toBasicDto(Farm farm);
    List<FarmBasicResponseDto> toBasicDto(List<Farm> farms);
    FarmDetailedResponseDto toDetailedDto(Farm farm);
    List<FarmDetailedResponseDto> toDetailedDto(List<Farm> farm);
//    Farm toEntity(FarmDetailedResponseDto detailedResponseDto);
//    List<FarmDetailedResponseDto> toDetailedDto(List<Farm> farms);
//    Farm toEntity(FarmUpdateRequestDto farmUpdateRequestDto);
    Farm toEntity(FarmCreateRequestDto farmCreateRequestDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(FarmUpdateRequestDto farmUpdateRequestDto, @MappingTarget Farm farm);


}