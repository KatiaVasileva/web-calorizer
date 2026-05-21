package com.vasileva.calorizer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    @Column(name = "food_category")
    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

    private Double calories;

    private Double protein;

    private Double fats;

    private Double carbohydrates;
}
