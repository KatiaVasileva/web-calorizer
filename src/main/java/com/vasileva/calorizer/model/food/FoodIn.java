package com.vasileva.calorizer.model.food;

import com.vasileva.calorizer.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodIn {

    @Positive
    Long id;

    @NotBlank(message = "Необходимо указать название")
    @Size(min = 3, max = 125)
    String name;

    @Size(min = 2, max = 125)
    String brand;

    FoodCategory foodCategory;

    @Min(value = 0, message = "Значение не может быть меньше 0")
    @Max(value = 900, message = "Значение не может быть больше 900")
    Double calories;

    @Min(value = 0, message = "Значение не может быть меньше 0")
    @Max(value = 100, message = "Значение не может быть больше 100")
    Double proteins;

    @Min(value = 0, message = "Значение не может быть меньше 0")
    @Max(value = 100, message = "Значение не может быть больше 100")
    Double fats;

    @Min(value = 0, message = "Значение не может быть меньше 0")
    @Max(value = 100, message = "Значение не может быть больше 100")
    Double carbohydrates;
}
