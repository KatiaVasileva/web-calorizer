package com.vasileva.calorizer.repository;

import com.vasileva.calorizer.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUser_Name(String username);
}
