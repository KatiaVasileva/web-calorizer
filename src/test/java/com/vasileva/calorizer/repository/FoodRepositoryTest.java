package com.vasileva.calorizer.repository;

import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class FoodRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FoodRepository foodRepository;

    @Test
    @DisplayName("test find food by id")
    public void shouldFindFoodById() {
        Food food = TestDataFactory.createFood();
        entityManager.persist(food);
        entityManager.flush();

        Optional<Food> result = foodRepository.findById(food.getId());

        assertTrue(result.isPresent());
        assertEquals(food.getName(), result.get().getName());
    }

    @Test
    public void shouldReturnTrueIfExists() {
        String existingName = "test_" + System.currentTimeMillis();
        Food food = TestDataFactory.createFood();
        food.setName(existingName);

        entityManager.persist(food);
        entityManager.flush();

        boolean result = foodRepository.existsByName(existingName);

        assertTrue(result, "Expected existsByName to return true for existing food name");
    }

    @Test
    public void shouldReturnFalseIfNotFound() {
        String nonExistingName = "test_" + System.currentTimeMillis();

        boolean result = foodRepository.existsByName(nonExistingName);

        assertFalse(result, "Expected existsByName to return false for non-existing food name");
    }
}
