package com.vasileva.calorizer.repository;

import com.vasileva.calorizer.annotation.IntegrationTest;
import com.vasileva.calorizer.model.food.Food;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IntegrationTest
public class FoodRepositoryDBTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    void testSaveUFood() {
        Food food = new Food();
        food.setName("Water");

        Food savedFood = foodRepository.save(food);
        assertThat(savedFood.getId()).isNotNull();
        assertThat(savedFood.getName()).isEqualTo("Water");
    }
}
