package com.vasileva.calorizer.model.user;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserIn {
    @Positive
    Long id;

    @NotBlank(message = "Необходимо указать имя")
    @Size(min = 2, max = 64)
    String name;

    @NotBlank(message = "Необходимо указать электронную почту")
    @Size(min = 8, max = 64)
    @Email
    String email;

    Gender gender;

    @Positive(message = "Вес не может быть меньше 0")
    int weight;

    @Positive(message = "Рост не может быть меньше 0")
    int height;

    @Positive(message = "Возраст не может быть меньше 0")
    int age;

    ActivityFactor activityFactor;
}
