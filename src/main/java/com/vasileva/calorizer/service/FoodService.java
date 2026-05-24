package com.vasileva.calorizer.service;

import com.vasileva.calorizer.mapper.FoodMapper;
import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    public final FoodRepository foodRepository;
    public final FoodMapper foodMapper;

    public FoodOut addFood(FoodIn input) {
        Food food = foodMapper.in(input);
        food.setCreated(LocalDateTime.now());
        return foodMapper.out(foodRepository.save(food));
    }

    public List<FoodOut> getAllFoods() {
        return foodRepository
                .findAll()
                .stream()
                .map(foodMapper::out)
                .collect(Collectors.toList());
    }

    public FoodOut getFoodById(Long id) {
        return foodRepository.findById(id).map(foodMapper::out)
                .orElseThrow(() -> new IllegalArgumentException("Food not found"));
    }

    public FoodOut updateFood(FoodIn input) {
        Food updatedFood = foodRepository.findById(input.getId())
                .orElseThrow(() -> new IllegalArgumentException("Food not found"));
        if (!updatedFood.getName().equals(input.getName())) {

            boolean exists = foodRepository.existsByName(input.getName());

            if (exists) {
                throw new IllegalArgumentException("Food with this name already exists");
            }

            updatedFood.setName(input.getName());
        }
        updatedFood.setBrand(input.getBrand());
        updatedFood.setFoodCategory(input.getFoodCategory());
        updatedFood.setCalories(input.getCalories());
        updatedFood.setFats(input.getFats());
        updatedFood.setCarbohydrates(input.getCarbohydrates());
        updatedFood.setProtein(input.getProtein());
        updatedFood.setUpdated(LocalDateTime.now());
        return foodMapper.out(foodRepository.save(updatedFood));
    }

    public void deleteFoodById(Long id) {
        foodRepository.deleteById(id);
    }
}
