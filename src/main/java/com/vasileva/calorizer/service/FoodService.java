package com.vasileva.calorizer.service;

import com.vasileva.calorizer.entity.Food;
import com.vasileva.calorizer.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    public final FoodRepository foodRepository;

    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food findFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Food not found"));
    }

    public Food updateFood(Food food) {
        Food updatedFood = foodRepository.findById(food.getId())
                .orElseThrow(() -> new IllegalArgumentException("Food not found"));
        updatedFood.setName(food.getName());
        updatedFood.setBrand(food.getBrand());
        updatedFood.setFoodCategory(food.getFoodCategory());
        updatedFood.setCalories(food.getCalories());
        updatedFood.setFats(food.getFats());
        updatedFood.setCarbohydrates(food.getCarbohydrates());
        return foodRepository.save(updatedFood);
    }

    public void deleteFoodById(Long id) {
        foodRepository.deleteById(id);
    }
}
