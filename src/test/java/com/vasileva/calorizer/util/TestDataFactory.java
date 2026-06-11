package com.vasileva.calorizer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodCategory;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;

import java.util.List;

public class TestDataFactory {

    public static final Long ID = 1L;
    public static final Long NON_EXISTING_ID = 999L;
    private static final String NAME = "testFood";
    private static final String BRAND = "testBrand";
    private static final FoodCategory CATEGORY = FoodCategory.DRINKS;
    private static final double CALORIES = 100d;
    private static final double PROTEINS = 10d;
    private static final double CARBOHYDRATES = 10d;
    private static final double FATS = 10d;

    private static Food.FoodBuilder baseFoodBuilder() {
        return Food.builder()
                .name(NAME)
                .brand(BRAND)
                .foodCategory(CATEGORY)
                .calories(CALORIES)
                .proteins(PROTEINS)
                .carbohydrates(CARBOHYDRATES)
                .fats(FATS);
    }

    private static FoodOut.FoodOutBuilder baseFoodOutBuilder() {
        return FoodOut.builder()
                .name(NAME)
                .brand(BRAND)
                .foodCategory(CATEGORY)
                .calories(CALORIES)
                .proteins(PROTEINS)
                .carbohydrates(CARBOHYDRATES)
                .fats(FATS);
    }

    private static FoodIn.FoodInBuilder baseFoodInBuilder() {
        return FoodIn.builder()
                .name(NAME)
                .brand(BRAND)
                .foodCategory(CATEGORY)
                .calories(CALORIES)
                .proteins(PROTEINS)
                .carbohydrates(CARBOHYDRATES)
                .fats(FATS);
    }

    public static Food createFood() {
        return baseFoodBuilder()
                .build();
    }

    public static FoodIn createFoodIn() {
        return baseFoodInBuilder().build();
    }

    public static FoodIn createUpdatedFood() {
        return baseFoodInBuilder()
                .name("updatedName")
                .brand("updatedBrand")
                .build();
    }

    public static FoodOut createFoodOut() {
        return baseFoodOutBuilder()
                .id(1L)
                .build();
    }

    public static List<Food> createFoodList() {
        Food food1 = createFood();
        Food food2 = baseFoodBuilder()
                .name("testFood2")
                .brand("testBrand2")
                .calories(200d)
                .proteins(20d)
                .carbohydrates(20d)
                .fats(20d)
                .build();
        food2.setName("testFood2");
        return List.of(food1, food2);
    }

    public static List<FoodOut> createFoodOutList() {
        return List.of(
                baseFoodOutBuilder()
                        .id(1L)
                        .build(),

                baseFoodOutBuilder()
                        .id(2L)
                        .name("testFood2")
                        .brand("testBrand2")
                        .calories(200d)
                        .proteins(20d)
                        .carbohydrates(20d)
                        .fats(20d)
                        .build()
        );
    }

    public static String createFoodJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(createFoodIn());
    }
}