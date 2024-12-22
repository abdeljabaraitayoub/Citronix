package org.hidxop.citronix.utils.factories;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hidxop.citronix.domain.entitiy.Field;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor(force = true)
@Component
public class FieldFactory {
    private final Faker faker=new Faker();
    public Field createDefault() {
        double area = BigDecimal.valueOf(faker.number().randomDouble(4, 1000, 5000))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();

        return Field.builder()
                .id(UUID.randomUUID())
                .area(area)
                .trees(new ArrayList<>())
                .build();
    }

    public List<Field> createMany(int number){
        List<Field> fields = new ArrayList<>();
        for (int i=0;i<=number;i++){
        fields.add(createDefault());
        }
        return fields;
    }




}