package com.vasileva.calorizer.model.user;

import lombok.Getter;

@Getter
public enum ActivityFactor {
    LOW("Низкая", 1.2),
    MEDIUM("Средняя", 1.375),
    HIGH("Повышенная", 1.55),
    VERY_HIGH("Высокая", 1.725),
    EXTREME("Экстремальная", 1.9);

    private final String value;
    private final double factor;

    ActivityFactor(String value, double factor) {
        this.value = value;
        this.factor = factor;
    }
}
