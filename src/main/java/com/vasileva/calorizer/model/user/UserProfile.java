package com.vasileva.calorizer.model.user;

import com.vasileva.calorizer.model.food.Food;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    @Enumerated(EnumType.STRING)
    Gender gender;

    BigDecimal weight;

    Integer height;

    Integer age;

    @Column(name = "activity_factor")
    @Enumerated(EnumType.STRING)
    ActivityFactor activityFactor;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Food> foods;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
