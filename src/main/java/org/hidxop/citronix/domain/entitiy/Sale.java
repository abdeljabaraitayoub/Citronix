package org.hidxop.citronix.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @CreationTimestamp
    @Column(name = "sale_date",nullable = false)
    private LocalDateTime saleDate;
    @Column(name = "unit_price")
    private double unitPrice;
    private double quantity;
    private String client;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;
}
