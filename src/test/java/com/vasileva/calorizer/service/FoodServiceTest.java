package com.vasileva.calorizer.service;

import com.vasileva.calorizer.exception.FoodExistsException;
import com.vasileva.calorizer.exception.FoodNotFoundException;
import com.vasileva.calorizer.mapper.FoodMapper;
import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.repository.FoodRepository;
import com.vasileva.calorizer.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static com.vasileva.calorizer.util.TestDataFactory.ID;
import static com.vasileva.calorizer.util.TestDataFactory.NON_EXISTING_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private FoodMapper foodMapper;

    @InjectMocks
    private FoodService foodService;

    private final FoodIn foodIn = TestDataFactory.createFoodIn();
    private final FoodIn updatedFoodIn = TestDataFactory.createUpdatedFood();
    private final Food food = TestDataFactory.createFood();
    private final FoodOut foodOut = TestDataFactory.createFoodOut();
    private final List<Food> foods = TestDataFactory.createFoodList();
    private final List<FoodOut> foodOuts = TestDataFactory.createFoodOutList();

    @Test
    @DisplayName("addFood: should successfully save Food and return FoodOut")
    void addFood_ShouldSaveAndReturnFoodOut() {
        when(foodMapper.in(foodIn)).thenReturn(food);
        when(foodRepository.save(any(Food.class))).thenReturn(food);
        when(foodMapper.out(food)).thenReturn(foodOut);

        FoodOut result = foodService.addFood(foodIn);

        assertNotNull(result);
        assertEquals(foodOut.getId(), result.getId());
        assertEquals(foodOut.getName(), result.getName());
        verify(foodRepository).save(food);
        assertNotNull(food.getCreated());
    }

    @Test
    @DisplayName("getAllFoods: should return list of all products")
    void getAllFoods_ShouldReturnListOfFoodOut() {
        when(foodRepository.findAll()).thenReturn(foods);
        when(foodMapper.out(foods.get(0))).thenReturn(foodOuts.get(0));
        when(foodMapper.out(foods.get(1))).thenReturn(foodOuts.get(1));

        List<FoodOut> result = foodService.getAllFoods();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(foodOuts.get(0).getName(), result.get(0).getName());
        assertEquals(foodOuts.get(1).getName(), result.get(1).getName());
    }

    @Test
    @DisplayName("getAllSortedByField: should return sorted list of products")
    void getAllSortedByField_ShouldReturnSortedList() {
        String sortField = "name";
        Sort expectedSort = Sort.by(Sort.Direction.ASC, sortField);

        when(foodRepository.findAll(expectedSort)).thenReturn(List.of(food));
        when(foodMapper.out(food)).thenReturn(foodOut);

        List<FoodOut> result = foodService.getAllSortedByField(sortField);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(foodRepository).findAll(expectedSort);
    }

    @Test
    @DisplayName("getFoodById: should return food by ID")
    void getFoodById_WithExistingId_ShouldReturnFoodOut() {

        when(foodRepository.findById(ID)).thenReturn(Optional.of(food));
        when(foodMapper.out(food)).thenReturn(foodOut);

        FoodOut result = foodService.getFoodById(ID);

        assertNotNull(result);
        assertEquals(ID, result.getId());
    }

    @Test
    @DisplayName("getFoodById: should throw FoodNotFoundException if ID not found")
    void getFoodById_WithNonExistingId_ShouldThrowNotFoundException() {
        when(foodRepository.findById(NON_EXISTING_ID)).thenReturn(Optional.empty());

        FoodNotFoundException exception = assertThrows(FoodNotFoundException.class,
                () -> foodService.getFoodById(NON_EXISTING_ID));

        assertEquals("Food with id=999 not found", exception.getMessage());
    }

    @Test
    @DisplayName("updateFood: should update food without change in the name (no check for unique)")
    void updateFood_WithoutNameChange_ShouldUpdateSuccessfully() {
        food.setName("testFood");

        when(foodRepository.findById(ID)).thenReturn(Optional.of(food));
        when(foodRepository.save(any(Food.class))).thenReturn(food);
        when(foodMapper.out(food)).thenReturn(foodOut);

        FoodOut result = foodService.updateFood(foodIn, ID);

        assertNotNull(result);
        assertNotNull(food.getUpdated());
        verify(foodRepository, never()).existsByName(anyString());
    }

    @Test
    @DisplayName("updateFood: should update food with new name if unique name")
    void updateFood_WithNewUniqueName_ShouldUpdateSuccessfully() {

        when(foodRepository.findById(ID)).thenReturn(Optional.of(food));
        when(foodRepository.existsByName(updatedFoodIn.getName())).thenReturn(false);
        when(foodRepository.save(any(Food.class))).thenReturn(food);
        when(foodMapper.out(food)).thenReturn(foodOut);

        FoodOut result = foodService.updateFood(updatedFoodIn, ID);

        assertNotNull(result);
        assertEquals("updatedName", food.getName());
        assertNotNull(food.getUpdated());
    }

    @Test
    @DisplayName("updateFood: should throw FoodExistsException if new name already exists")
    void updateFood_WithExistingName_ShouldThrowFoodExistsException() {
        when(foodRepository.findById(ID)).thenReturn(Optional.of(food));
        when(foodRepository.existsByName(updatedFoodIn.getName())).thenReturn(true);

        FoodExistsException exception = assertThrows(FoodExistsException.class,
                () -> foodService.updateFood(updatedFoodIn, ID));

        assertEquals("Food with name=updatedName already exists", exception.getMessage());
        verify(foodRepository, never()).save(any(Food.class));
    }

    @Test
    @DisplayName("updateFood: should throw FoodNotFoundException when updating non-existing food")
    void updateFood_WithNonExistingId_ShouldThrowNotFoundException() {
        FoodIn input = TestDataFactory.createFoodIn();

        when(foodRepository.findById(NON_EXISTING_ID)).thenReturn(Optional.empty());

        assertThrows(FoodNotFoundException.class, () -> foodService.updateFood(input, NON_EXISTING_ID));
    }

    @Test
    @DisplayName("deleteFoodById: should delete from repository")
    void deleteFoodById_ShouldInvokeRepositoryDelete() {

        foodService.deleteFoodById(ID);

        verify(foodRepository, times(1)).deleteById(ID);
    }
}
