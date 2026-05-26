package com.vasileva.calorizer.model.food;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodOut {

    Long id;

    String name;

    String brand;

    FoodCategory foodCategory;

    Double calories;

    Double proteins;

    Double fats;

    Double carbohydrates;

    LocalDateTime created;

    LocalDateTime updated;

}
