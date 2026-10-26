package com.vasileva.calorizer.mapper;

import com.vasileva.calorizer.model.food.Food;
import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(target = "userProfileId", source = "userProfile.id")
    FoodOut out(Food food);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "brand", source = "brand",
            qualifiedByName = "normalizeBrand")
    @Mapping(target = "isFavorite", constant = "false")
    @Mapping(target = "userProfile", ignore = true)
    Food in(FoodIn foodIn);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", source = "brand",
            qualifiedByName = "normalizeBrand")
    @Mapping(target = "isFavorite", ignore = true)
    @Mapping(target = "updatedAt",
            expression = "java(java.time.LocalDateTime.now())"
    )
    void update(FoodIn foodIn, @MappingTarget Food food);

    @Named("normalizeBrand")
    default String normalizeBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            return "–";
        }

        return brand.trim();
    }
}
