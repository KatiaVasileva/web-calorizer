package com.vasileva.calorizer.model.user;

import java.math.BigDecimal;

public record UserProfileOut(String name,
                             Gender gender,
                             BigDecimal weight,
                             Integer height,
                             Integer age) {
}
