package org.hidxop.citronix.utils.factories;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.domain.entitiy.Tree;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TreeFactory {
    private final Faker faker;

    public Tree createDefault() {
        return Tree.builder()
                .id(UUID.randomUUID())
                .plantedAt(LocalDateTime.now().minusDays(faker.number().numberBetween(1,200)))
                .build();
    }


    public List<Tree> createMany(int number) {
        List<Tree> trees = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            trees.add(createDefault());
        }
        return trees;
    }


}