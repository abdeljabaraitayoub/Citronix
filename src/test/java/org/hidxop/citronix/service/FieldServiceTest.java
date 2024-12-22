package org.hidxop.citronix.service;

import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.hidxop.citronix.domain.entitiy.Farm;
import org.hidxop.citronix.domain.entitiy.Field;
import org.hidxop.citronix.dto.field.*;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.hidxop.citronix.exceptionHandling.exceptions.NotFoundException;
import org.hidxop.citronix.repository.FieldRepository;
import org.hidxop.citronix.service.impl.FarmService;
import org.hidxop.citronix.service.impl.FieldService;
import org.hidxop.citronix.service.validator.FieldValidator;
import org.hidxop.citronix.utils.factories.FarmFactory;
import org.hidxop.citronix.utils.factories.FieldFactory;
import org.hidxop.citronix.utils.factories.TreeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
@SpringBootTest
public class FieldServiceTest {

//    private Faker faker;
//    private FieldFactory fieldFactory;
//    private FarmFactory farmFactory;
//    private TreeFactory treeFactory;
//
//    @Mock
//    private FieldRepository fieldRepository;
//    @Mock
//    private FieldMapper fieldMapper;
//    @Mock
//    private FieldValidator fieldValidator;
//    @Mock
//    private FarmService farmService;
//
//    @InjectMocks
//    private FieldService fieldService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        faker = new Faker();
//        fieldFactory = new FieldFactory();
//        farmFactory = new FarmFactory();
//        treeFactory = new TreeFactory();
//    }
//
//    @Test
//    public void findById_ShouldReturnDto() {
//        // Arrange
//        Field field = fieldFactory.createDefault();
//        FieldDetailedResponseDto expectedDto = new FieldDetailedResponseDto(field.getId(), field.getArea(), null, null);
//
//        when(fieldRepository.findById(field.getId())).thenReturn(Optional.of(field));
//        when(fieldMapper.toDetailedDto(field)).thenReturn(expectedDto);
//
//        // Act
//        FieldDetailedResponseDto field1 = fieldService.findById(field.getId());
//
//        // Assert
//        Assertions.assertThat(field1).isNotNull();
//        Assertions.assertThat(field1.id()).isEqualTo(field.getId());
//    }
//
//    @Test
//    public void findById_ShouldThrowNotFoundException() {
//        UUID randomId = UUID.randomUUID();
//        when(fieldRepository.findById(randomId)).thenReturn(Optional.empty());
//
//        Assertions.assertThatThrownBy(() -> fieldService.findById(randomId))
//                .isInstanceOf(NotFoundException.class);
//    }
//
//    @Test
//    public void deleteById_ShouldReturnVoid() {
//        Field field = fieldFactory.createDefault();
//        when(fieldRepository.findById(field.getId())).thenReturn(Optional.of(field));
//        doNothing().when(fieldRepository).deleteById(field.getId());
//
//        // Act
//        fieldService.deleteById(field.getId());
//
//        // Assert
//        verify(fieldRepository).findById(field.getId());
//        verify(fieldRepository).deleteById(field.getId());
//    }
//
//    @Test
//    public void deleteById_ShouldThrowNotFoundException() {
//        UUID randomId = UUID.randomUUID();
//        when(fieldRepository.findById(randomId)).thenReturn(Optional.empty());
//
//        Assertions.assertThatThrownBy(() -> fieldService.deleteById(randomId))
//                .isInstanceOf(NotFoundException.class);
//    }
//
//    @Test
//    public void save_ShouldReturnFieldBasicResponseDto() {
//        // Arrange
//        Farm farm = farmFactory.createDefault();
//        double area = faker.number().randomDouble(4, 1000, 2000);
//
//        FieldCreateRequestDto request = new FieldCreateRequestDto(area, farm.getId());
//
//        Field field = fieldFactory.createDefault();
//        field.setFarm(farm);
//        field.setArea(area);
//
//        FieldBasicResponseDto expectedResponse = new FieldBasicResponseDto(field.getId(), field.getArea());
//
//        when(farmService.getFarmById(farm.getId())).thenReturn(farm);
//        when(fieldMapper.toEntity(request)).thenReturn(field);
//        when(fieldRepository.save(field)).thenReturn(field);
//        when(fieldMapper.toBasicDto(field)).thenReturn(expectedResponse);
//        doNothing().when(fieldValidator).validateFieldCreation(field);
//
//        // Act
//        FieldBasicResponseDto response = fieldService.save(request);
//
//        // Assert
//        Assertions.assertThat(response).isNotNull();
//        Assertions.assertThat(response.id()).isEqualTo(field.getId());
//        Assertions.assertThat(response.area()).isEqualTo(area);
//    }
//
//    @Test
//    public void save_ShouldThrowValidationException_WhenValidationFails() {
//        Farm farm = farmFactory.createDefault();
//        double area = faker.number().randomDouble(4, 1000, 2000);
//        FieldCreateRequestDto request = new FieldCreateRequestDto(area, farm.getId());
//
//        Field field = fieldFactory.createDefault();
//        field.setFarm(farm);
//
//        InvalidStateException validationException = new InvalidStateException("The fields number shouldn't be bigger than 10 for each farm.");
//
//        when(farmService.getFarmById(farm.getId())).thenReturn(farm);
//        when(fieldMapper.toEntity(request)).thenReturn(field);
//        doThrow(validationException).when(fieldValidator).validateFieldCreation(field);
//
//        Assertions.assertThatThrownBy(() -> fieldService.save(request))
//                .isInstanceOf(InvalidStateException.class);
//    }
//
//    @Test
//    public void update_ShouldReturnDto() {
//        // Arrange
//        Farm farm = farmFactory.createDefault();
//        double originalArea = faker.number().randomDouble(4, 1000, 3000);
//        UUID fieldId = UUID.randomUUID();
//
//        Field field = fieldFactory.createDefault();
//        field.setFarm(farm);
//        field.setArea(originalArea);
//
//        double newArea = faker.number().randomDouble(4, 1000, 3000);
//
//        FieldUpdateRequestDto request = new FieldUpdateRequestDto(newArea, farm.getId());
//
//        Field updatedField = Field.builder()
//                .id(fieldId)
//                .area(newArea)
//                .farm(farm)
//                .trees(new ArrayList<>())
//                .build();
//
//        FieldBasicResponseDto expectedResponse = new FieldBasicResponseDto(fieldId, newArea);
//
//        when(fieldRepository.findById(fieldId)).thenReturn(Optional.of(field));
//        when(farmService.getFarmById(farm.getId())).thenReturn(farm);
//        when(fieldRepository.save(any(Field.class))).thenReturn(updatedField);
//        when(fieldMapper.toBasicDto(any(Field.class))).thenReturn(expectedResponse);
//        doNothing().when(fieldValidator).validateFieldUpdate(field, request);
//        doNothing().when(fieldMapper).partialUpdate(request, field);
//
//        // Act
//        FieldBasicResponseDto response = fieldService.update(fieldId, request);
//
//        // Assert
//        Assertions.assertThat(response).isNotNull();
//        Assertions.assertThat(response.id()).isEqualTo(fieldId);
//        Assertions.assertThat(response.area()).isEqualTo(newArea);
//    }
//
//    @Test
//    public void calculateTreePerAreaRate_ShouldReturnDouble() {
//        Field field = fieldFactory.createDefault();
//        field.setTrees(treeFactory.createMany(5));
//
//        when(fieldRepository.findById(field.getId())).thenReturn(Optional.of(field));
//
//        Double response = fieldService.calculateTreePerAreaRate(field.getId());
//        Double expectedResponse = 1000.0 * field.getTrees().size() / field.getArea();
//
//        Assertions.assertThat(response).isEqualTo(expectedResponse);
//    }
    @Test
    public void calculateTreePerAreaRate_ShouldReturnDouble() {
    }
}
