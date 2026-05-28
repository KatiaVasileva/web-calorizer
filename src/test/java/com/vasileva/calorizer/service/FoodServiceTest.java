package com.vasileva.calorizer.service;

import com.vasileva.calorizer.mapper.FoodMapper;
import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.repository.FoodRepository;
import com.vasileva.calorizer.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private FoodMapper foodMapper;

    @InjectMocks
    private FoodService foodService;

    private final FoodIn updatedFoodIn = TestDataFactory.createUpdatedFood();
    private final Food food = TestDataFactory.createFood();

    @Test
    public void shouldReturnUpdatedFood() {
        Food updatedFood = TestDataFactory.createFood();
        updatedFood.setBrand("updatedBrand");
        FoodOut updatedFoodOut = TestDataFactory.createFoodOut();
        updatedFoodOut.setBrand("updatedBrand");

        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
        when(foodRepository.existsByName("updatedName"))
                .thenReturn(false);
        when(foodRepository.save(food)).thenReturn(updatedFood);
        when(foodMapper.out(updatedFood)).thenReturn(updatedFoodOut);

        FoodOut result = foodService.updateFood(updatedFoodIn, 1L);

        assertEquals(updatedFood.getBrand(), result.getBrand());
        verify(foodRepository, times(1)).findById(1L);
        verify(foodRepository, times(1)).existsByName("updatedName");
        verify(foodRepository, times(1)).save(food);
        verify(foodMapper, times(1)).out(updatedFood);
    }
}
