package org.hidxop.citronix.service.validator;

import lombok.RequiredArgsConstructor;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.domain.entitiy.Harvest;
import org.hidxop.citronix.domain.enumeration.Season;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HarvestValidator {

public void validateCreateHarvest(Field field, List<Harvest> harvests, LocalDateTime date) {
    validateHarvestEachSeason(field, harvests,date,null);

}

 public void validateUpdateHarvest(Field field, List<Harvest> harvests, LocalDateTime date,Harvest harvest) {
     validateHarvestEachSeason(field, harvests,date,harvest);

 }


    public void validateHarvestEachSeason(Field field, List<Harvest> harvests, LocalDateTime date, Harvest currentHarvest) {
        int currentYear =date.getYear();
        Season season = getSeason(date);
        if (!(season==Season.SPRING)){
            throw new InvalidStateException(
                    String.format("Field %s must have a harvest only in Spring Season.",
                            field.getId())
            );
        }
        boolean existingHarvestFound = harvests.stream()
                .filter(harvest -> currentHarvest == null || !harvest.getId().equals(currentHarvest.getId()))
                .filter(h -> season.equals(h.getSeason()) &&
                       h.getHarvestDate().getYear() == currentYear)
                .anyMatch(h -> h.getHarvestDetails().stream()
                        .anyMatch(d -> d.getTree().getField().getId().equals(field.getId())));
        if (existingHarvestFound) {
            throw new InvalidStateException(
                    String.format("Field %s already has a harvest in %s season of %d.",
                            field.getId(), season, currentYear)
            );
        }

    }

    private Season getSeason(LocalDateTime date) {
        return switch (date.getMonthValue()) {
            case 12,1,2 -> Season.WINTER;
            case 3,4,5 -> Season.SPRING;
            case 6, 7,8 -> Season.SUMMER;
            case 9,10,11 -> Season.FALL;
            default -> throw new IllegalStateException("Unexpected value: " + LocalDate.now().getMonthValue());
        };

    }


}
