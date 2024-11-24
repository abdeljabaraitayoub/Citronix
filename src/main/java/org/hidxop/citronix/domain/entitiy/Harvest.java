package org.hidxop.citronix.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hidxop.citronix.domain.enumeration.Season;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "harvests")
@Entity
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime  harvestDate;

    @Transient
    private Season season;


    private  double totalQuantity;

    @OneToMany(mappedBy = "harvest",cascade = CascadeType.REMOVE ,orphanRemoval = true)
    private List<HarvestDetail> harvestDetails;

    @OneToMany(mappedBy = "harvest")
    private List<Sale> sale;

   public void addHarvestDetail(HarvestDetail detail) {
    if (detail != null) {
        harvestDetails.add(detail);
        detail.setHarvest(this);
    }
}

public void removeHarvestDetail(HarvestDetail detail) {
    if (detail != null && harvestDetails.contains(detail)) {
        harvestDetails.remove(detail);
        detail.setHarvest(null);
    }
}
    @PostLoad
    private void determineSeason() {
        Month month = harvestDate.getMonth();
       switch (month) {
            case DECEMBER, JANUARY, FEBRUARY ->this.season=Season.WINTER;
            case MARCH, APRIL, MAY ->this.season= Season.SPRING;
            case JUNE, JULY, AUGUST ->this.season= Season.SUMMER;
            case SEPTEMBER, OCTOBER, NOVEMBER ->this.season= Season.FALL;
        };
    }

    @PreUpdate
    @PrePersist
    private void calculateTotalQuantity() {
        this.totalQuantity=harvestDetails.stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();
        determineSeason();
    }


}
