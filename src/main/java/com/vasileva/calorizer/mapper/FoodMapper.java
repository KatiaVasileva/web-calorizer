package com.vasileva.calorizer.mapper;

import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    FoodOut out(Food food);

    Food in(FoodIn foodIn);

}
