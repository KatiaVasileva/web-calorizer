package com.vasileva.calorizer;

import com.vasileva.calorizer.entity.Food;
import com.vasileva.calorizer.entity.FoodCategory;
import com.vasileva.calorizer.service.FoodService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CalorizerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CalorizerApplication.class, args);
        FoodService foodService = context.getBean(FoodService.class);
//
//        Food food = Food.builder()
//                .name("Миндаль сырой")
//                .brand("Жизнь Март")
//                .foodCategory(FoodCategory.NUTS)
//                .calories(610d)
//                .protein(18d)
//                .fats(53d)
//                .carbohydrates(14d)
//                .build();
//
//        foodService.addFood(food);

        foodService.getAllFoods().forEach(System.out::println);

    }

}
