package com.vasileva.calorizer.controller;

import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.service.FoodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/foods")
@AllArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public Collection<FoodOut> getAll() {
        return foodService.getAllFoods();
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
