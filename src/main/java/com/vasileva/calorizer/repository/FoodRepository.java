package com.vasileva.calorizer.repository;

import com.vasileva.calorizer.model.food.Food;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

    boolean existsByName(@NotBlank(message = "Необходимо указать название") @Size(min = 3, max = 125) String name);
}
