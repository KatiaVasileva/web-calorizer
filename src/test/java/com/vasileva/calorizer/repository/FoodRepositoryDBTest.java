package com.vasileva.calorizer.repository;

import com.vasileva.calorizer.config.PostgresContainerTest;
import com.vasileva.calorizer.model.food.Food;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Testcontainers
public class FoodRepositoryDBTest extends PostgresContainerTest {

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
