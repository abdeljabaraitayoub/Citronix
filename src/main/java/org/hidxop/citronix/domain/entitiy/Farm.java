package org.hidxop.citronix.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "farms")
@Entity
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String location;
    private double totalArea;
    @OneToMany(mappedBy = "farm",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Field> fields;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

}