package com.vasileva.calorizer.model.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserOut {
    Long id;

    String name;

    String email;

    Gender gender;

    int weight;

    int height;

    int age;

    ActivityFactor activityFactor;

    LocalDate created;
}
