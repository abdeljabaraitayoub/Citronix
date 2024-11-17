package org.hidxop.citronix.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trees")
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "sale_date")
    private LocalDateTime saleDate;
    @Column(name = "unit_price")
    private double unitPrice;
    private double quantity;
    private String client;
    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;
}
