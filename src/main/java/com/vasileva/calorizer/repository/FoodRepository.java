package com.vasileva.calorizer.repository;

import com.vasileva.calorizer.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
