package com.vasileva.calorizer.controller;

import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.service.FoodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/foods")
@AllArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public String getAll(Model model) {
        Collection<FoodOut> foods = foodService.getAllFoods();
        model.addAttribute("foods", foods);
        return "food-list";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodOut addFood(@RequestBody @Valid FoodIn input) {
        return foodService.addFood(input);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FoodOut updateFood(@RequestBody @Valid FoodIn input, @PathVariable int id) {
        return foodService.updateFood(input);
    }

    @GetMapping("/{id}")
    public FoodOut getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWriterById(@PathVariable Long id) {
         foodService.deleteFoodById(id);
    }
}
