package org.hidxop.citronix.service.validator;

import lombok.RequiredArgsConstructor;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.domain.entitiy.Harvest;
import org.hidxop.citronix.domain.enumeration.Season;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return switch (LocalDate.now().getMonth()) {
            case DECEMBER, JANUARY, FEBRUARY -> Season.WINTER;
            case MARCH, APRIL, MAY -> Season.SPRING;
            case JUNE, JULY, AUGUST -> Season.SUMMER;
            case SEPTEMBER, OCTOBER, NOVEMBER -> Season.FALL;
        };

    }


}
