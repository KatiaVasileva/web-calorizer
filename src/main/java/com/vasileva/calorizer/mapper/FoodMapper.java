package com.vasileva.calorizer.mapper;

import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(target = "userId", source = "user.id")
    FoodOut out(Food food);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "brand", defaultValue = "–")
    @Mapping(target = "isFavorite", defaultValue = "false")
    @Mapping(target = "user", ignore = true)
    Food in(FoodIn foodIn);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", defaultValue = "–")
    @Mapping(target = "isFavorite", ignore = true)
    @Mapping(target = "updatedAt",
            expression = "java(java.time.LocalDateTime.now())"
    )
    void update(FoodIn foodIn, @MappingTarget Food food);

}
