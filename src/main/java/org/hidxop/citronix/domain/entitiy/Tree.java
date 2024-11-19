package org.hidxop.citronix.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hidxop.citronix.domain.enumeration.ProductivityStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trees")
@Entity
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Transient
    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_id")
    private Field field;

    @Column(name = "planted_at", nullable = false)
    private LocalDateTime plantedAt;

    @Transient
    private ProductivityStatus productivityStatus;

    @Transient
    private double seasonalProductivity;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HarvestDetail> harvestDetail = new ArrayList<>();

    public void addHarvestDetail(HarvestDetail detail) {
        if (detail != null) {
            harvestDetail.add(detail);
            detail.setTree(this);
        }
    }

    public void removeHarvestDetail(HarvestDetail detail) {
        if (detail != null && harvestDetail.contains(detail)) {
            harvestDetail.remove(detail);
            detail.setTree(null);
        }
    }

    @PostLoad
    @PreUpdate
    @PrePersist
    private void calculateDerivedFields() {
        if (this.plantedAt != null) {
            this.age = Period.between(this.plantedAt.toLocalDate(), LocalDate.now()).getYears();
            this.productivityStatus = calculateProductivityStatus();
            this.seasonalProductivity = calculateSeasonalProductivity();
        }
    }

    private ProductivityStatus calculateProductivityStatus() {
        if (age < 3) return ProductivityStatus.YOUNG;
        if (age <= 10) return ProductivityStatus.MATURE;
        return ProductivityStatus.OLD;
    }

    private double calculateSeasonalProductivity() {
        if (age < 3) return 2.5;
        if (age <= 10) return 12.0;
        return 20.0;
    }
}