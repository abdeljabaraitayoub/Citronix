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
@Table(name = "fields")
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private double area;
    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;
    @OneToMany(mappedBy = "field")
    private List<Tree> trees;
}
