package com.vasileva.calorizer.mapper;

import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    FoodOut out(Food food);

    @Mapping(
            target = "brand",
            defaultValue = "–"
    )
    Food in(FoodIn foodIn);

}
