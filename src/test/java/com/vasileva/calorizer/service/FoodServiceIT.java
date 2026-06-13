package com.vasileva.calorizer.service;

import com.vasileva.calorizer.annotation.IntegrationTest;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.util.TestDataFactory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@RequiredArgsConstructor
public class FoodServiceIT {
    private final FoodService foodService;

    private FoodOut foodOut;
    private final FoodIn updatedFood = TestDataFactory.createUpdatedFood();


    @BeforeEach
    void setUp() {
        foodOut = foodService.addFood(TestDataFactory.createFoodIn());
    }

    @AfterEach
    void tearDown() {
        foodService.deleteFoodById(foodOut.getId());
    }

    @Test
    public void getAllFoods() {
        List<FoodOut> result = foodService.getAllFoods();
        assertFalse(result.isEmpty());
        assertEquals(foodOut.getName(), result.getFirst().getName());
    }

    @Test
    public void getFoodById() {
        FoodOut result = foodService.getFoodById(foodOut.getId());
        assertNotNull(result);
        assertEquals(foodOut.getName(), result.getName());
    }

    @Test
    public void updateFood() {
        FoodOut result = foodService
                .updateFood(updatedFood, foodOut.getId());
        assertNotNull(result);
        assertEquals(updatedFood.getName(), result.getName());
    }
}
