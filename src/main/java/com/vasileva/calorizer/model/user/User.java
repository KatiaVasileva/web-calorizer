package com.vasileva.calorizer.model.user;

import com.vasileva.calorizer.model.food.Food;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String email;

    String password;

    @Enumerated(EnumType.STRING)
    Gender gender;

    int weight;

    int height;

    int age;

    @Column(name = "activity_factor")
    @Enumerated(EnumType.STRING)
    ActivityFactor activityFactor;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Food> foods;

    LocalDateTime createdAt;
}
