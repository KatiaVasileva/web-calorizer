package com.vasileva.calorizer.repository;

import com.vasileva.calorizer.model.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Optional<User> findByName(@NonNull String username);
}
