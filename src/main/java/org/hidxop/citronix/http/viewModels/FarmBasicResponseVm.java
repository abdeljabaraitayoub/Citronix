package org.hidxop.citronix.http.viewModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hidxop.citronix.dto.farm.FarmBasicResponseDto;

import java.time.Month;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmBasicResponseVm {
    private UUID id;
    private String name;
    private String location;
    private double totalArea;
    private Date createdAt;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Date {
        private int year;
        private int day;
        private Month month;
    }

    public static FarmBasicResponseVm toVm(FarmBasicResponseDto dto) {
        FarmBasicResponseVm vm = new FarmBasicResponseVm();
        vm.setId(dto.id());
        vm.setName(dto.name());
        vm.setLocation(dto.location());
        vm.setTotalArea(dto.totalArea());

        Date date = new Date(
                dto.createdAt().getYear(),
                dto.createdAt().getDayOfMonth(),
                dto.createdAt().getMonth()
        );
        vm.setCreatedAt(date);

        return vm;
    }

    public static List<FarmBasicResponseVm> toVm(List<FarmBasicResponseDto> dtos) {
        return dtos.stream()
                .map(FarmBasicResponseVm::toVm)
                .toList();
    }
}