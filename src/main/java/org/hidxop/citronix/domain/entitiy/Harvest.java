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
    @Transient
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
    @PreUpdate
    @PrePersist
    private void calculateDerivedFields() {
        if (this.harvestDate != null) {
            this.season = determineSeason();
            this.totalQuantity = calculateTotalQuantity();
        }
    }

    private Season determineSeason() {
        Month month = harvestDate.getMonth();
        return switch (month) {
            case DECEMBER, JANUARY, FEBRUARY -> Season.WINTER;
            case MARCH, APRIL, MAY -> Season.SPRING;
            case JUNE, JULY, AUGUST -> Season.SUMMER;
            case SEPTEMBER, OCTOBER, NOVEMBER -> Season.FALL;
        };
    }

    private double calculateTotalQuantity() {
        return harvestDetails.stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();
    }


}
