package com.vasileva.calorizer.model.food;

import com.vasileva.calorizer.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    @Builder.Default
    String brand = "–";

    @Column(name = "food_category")
    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory;

    Double calories;

    Double proteins;

    Double fats;

    Double carbohydrates;

    LocalDateTime created;

    LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    @ToString.Exclude
    User user;
}
