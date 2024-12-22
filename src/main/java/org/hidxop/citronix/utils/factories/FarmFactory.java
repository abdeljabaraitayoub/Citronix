package org.hidxop.citronix.utils.factories;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import org.hidxop.citronix.domain.entitiy.Farm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Component
public class FarmFactory {
    private final Faker faker = new Faker();

    public Farm createDefault() {
        return Farm.builder()
                .id(UUID.randomUUID())
                .name(faker.company().name() + " Farm")
                .location(faker.address().fullAddress())
                .build();
    }

    public List<Farm> createMany(int number) {
        List<Farm> farms = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            farms.add(createDefault());
        }
        return farms;
    }
}
