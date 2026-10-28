package com.vasileva.calorizer.model.user;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileUpdateIn {

    Gender gender;

    @PositiveOrZero(message = "Вес не может быть меньше 0")
    BigDecimal weight;

    @PositiveOrZero(message = "Рост не может быть меньше 0")
    Integer height;

    @PositiveOrZero(message = "Возраст не может быть меньше 0")
    Integer age;
}

