package com.vasileva.calorizer.mapper;

import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(target = "userId", source = "user.id")
    FoodOut out(Food food);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(
            target = "brand",
            defaultValue = "–"
    )
    @Mapping(target = "user", ignore = true)
    Food in(FoodIn foodIn);

}
