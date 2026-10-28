package com.vasileva.calorizer.controller;

import com.vasileva.calorizer.model.user.UserProfileOut;
import com.vasileva.calorizer.model.user.UserProfileUpdateIn;
import com.vasileva.calorizer.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/v1/profile"))
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public UserProfileOut getUserProfile (Authentication authentication) {
        return userProfileService.getUserProfile(authentication.getName());
    }

    @PatchMapping
    public UserProfileOut updateUserProfile (Authentication authentication,
                                             @RequestBody @Valid UserProfileUpdateIn request) {
        return userProfileService.updateUserProfile(authentication.getName(), request);
    }
}
