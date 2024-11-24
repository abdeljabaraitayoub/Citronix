package org.hidxop.citronix.domain.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", updatable = true)
    private Farm farm;

    @OneToMany(mappedBy = "field")
    private List<Tree> trees = new ArrayList<>();

    public void addTree(Tree tree) {
        if (trees == null) {
            trees = new ArrayList<>();
        }
        trees.add(tree);
        tree.setField(this);
    }

    public void removeTree(Tree tree) {
        if (trees != null) {
            trees.remove(tree);
            tree.setField(null);
        }
    }
}