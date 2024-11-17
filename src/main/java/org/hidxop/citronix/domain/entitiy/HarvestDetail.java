package org.hidxop.citronix.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HarvestDetails")
@Entity
public class HarvestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "tree_id")
    private Tree tree;
    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;
    private double quantity;
}
