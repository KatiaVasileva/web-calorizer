package com.vasileva.calorizer.repository;

import com.vasileva.calorizer.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
