package com.vasileva.calorizer.model.user;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterIn {
    @Positive
    Long id;

    @NotBlank(message = "Необходимо указать имя")
    @Size(min = 2, max = 64)
    String name;

    @NotBlank(message = "Необходимо указать электронную почту")
    @Size(min = 8, max = 64)
    @Email
    String email;

    @NotBlank(message = "Необходимо указать пароль")
    @Size(min = 8, max = 64)
    String password;
}
