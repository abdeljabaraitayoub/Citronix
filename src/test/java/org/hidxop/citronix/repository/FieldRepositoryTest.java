package org.hidxop.citronix.repository;

import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.hidxop.citronix.config.FakerConfig;
import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.domain.entitiy.Tree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(FakerConfig.class)
class FieldRepositoryTest {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private Faker faker;
    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private FarmRepository farmRepository;

    @Test
    void fieldRepository_Save_ReturnSavedField() {
        // Arrange
        Field field = Field.builder()
                .area(faker.number().randomDouble(3, 1000, 2000))
                .build();

        // Act
        Field savedField = fieldRepository.save(field);

        // Assert
        Assertions.assertThat(savedField).isNotNull();
        Assertions.assertThat(savedField.getId()).isNotNull();
    }


    @Test
    void fieldRepository_FindAll_ReturnFieldsList() {
        // Arrange
        Field field1 = Field.builder()
                .area(faker.number().randomDouble(3, 1000, 2000))
                .build();
        Field field2 = Field.builder()
                .area(faker.number().randomDouble(3, 1000, 2000))
                .build();

         fieldRepository.save(field1);
         fieldRepository.save(field2);
        // Act

        List<Field>fields =fieldRepository.findAll();

        // Assert
        Assertions.assertThat(fields.size()).isEqualTo(2);
        Assertions.assertThat(fields).isNotNull();
    }

    @Test
    void fieldRepository_FindByIdWithFarmAndTrees_ReturnSavedField() {
        // Arrange
        Farm farm = Farm.builder()
                .name(faker.company().name())
                .location(faker.address().fullAddress())
                .createdAt(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 30)))
                .build();
        farmRepository.save(farm);

        Field field1 = Field.builder()
                .area(faker.number().randomDouble(3, 1000, 2000))
                .farm(farm)
                .build();
        fieldRepository.save(field1);

        Tree tree1 = Tree.builder()
                .field(field1)
                .plantedAt(LocalDateTime.now().minusDays(faker.number().numberBetween(1,100)))
                .build();
        field1.addTree(tree1);

        Tree tree2 = Tree.builder()
                .field(field1)
                .plantedAt(LocalDateTime.now().minusDays(faker.number().numberBetween(1,100)))
                .build();
        field1.addTree(tree2);

        treeRepository.saveAll(Arrays.asList(tree1, tree2));

        // Act
        Field field = fieldRepository.findByIdWithFarmAndTrees(field1.getId()).get();

        // Assert
        Assertions.assertThat(field).isNotNull();
        Assertions.assertThat(field.getId()).isEqualTo(field1.getId());
        Assertions.assertThat(field.getArea()).isEqualTo(field1.getArea());
        Assertions.assertThat(field.getFarm()).isNotNull();
        Assertions.assertThat(field.getFarm().getId()).isEqualTo(farm.getId());
        Assertions.assertThat(field.getTrees()).hasSize(2);
        Assertions.assertThat(field.getTrees())
                .extracting("id")
                .containsExactlyInAnyOrder(tree1.getId(), tree2.getId());
    }


}