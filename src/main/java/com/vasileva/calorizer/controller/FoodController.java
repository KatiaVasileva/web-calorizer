package com.vasileva.calorizer.controller;

import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.service.FoodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foods")
@AllArgsConstructor
public class FoodController {
    private final FoodService foodService;

//    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

//    @GetMapping
//    public List<FoodOut> getAll() {
//        logger.info("Get all foods");
//        logger.debug("DEBUG: Additional debug information");
//        logger.error("ERROR: Simulated error log (just an example)");
//        return foodService.getAllFoods();
//    }

    @GetMapping("/sort")
    public List<FoodOut> getAllSortedByField(@RequestParam String field) {
        return foodService.getAllSortedByField(field);
    }

    @GetMapping("/sort-with-page")
    public Page<FoodOut> getAllSortedByFieldWithPagination(@RequestParam(defaultValue = "name") String field,
                                                           @PageableDefault Pageable pageable) {
        return foodService.getAllSortedByFieldWithPagination(field, pageable);
    }

    @GetMapping
    public Page<FoodOut> getAllBySearch(@RequestParam(defaultValue = "") String search,
                                        @RequestParam(defaultValue = "name") String field,
                                        @PageableDefault Pageable pageable) {
        return foodService.getAllSortedByFieldWithPaginationAndSearch(search, field, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodOut addFood(@RequestBody @Valid FoodIn input) {
        return foodService.addFood(input);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FoodOut updateFood(@RequestBody @Valid FoodIn input, @PathVariable Long id) {
        return foodService.updateFood(input, id);
    }

    @PatchMapping("/{id}/favorite")
    public FoodOut toggleFavorite(@PathVariable Long id) {
        return foodService.toggleFavorite(id);
    }

    @GetMapping("/{id}")
    public FoodOut getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFoodById(@PathVariable Long id) {
         foodService.deleteFoodById(id);
    }
}





