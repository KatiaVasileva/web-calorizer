package com.vasileva.calorizer.model.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterOut {
    Long id;

    String name;

    String email;

    LocalDateTime created;
}
