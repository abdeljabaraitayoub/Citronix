package org.hidxop.citronix.domain.entitiy;


import jakarta.persistence.*;
import lombok.*;
import org.hidxop.citronix.domain.enumeration.ProductivityStatus;

import java.time.LocalDateTime;
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
    private int age;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;
    @Column(name = "planted_at",nullable = false)
    private LocalDateTime plantedAt;
    private ProductivityStatus productivityStatus;
    private double seasonalProductivity;
    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HarvestDetail> harvestDetail;

    public void addHarvestDetail(HarvestDetail detail) {
        harvestDetail.add(detail);
        detail.setTree(this);
    }

    public void removeHarvestDetail(HarvestDetail detail) {
        harvestDetail.remove(detail);
        detail.setTree(null);
    }
}
