package com.vasileva.calorizer.service;

import com.vasileva.calorizer.exception.UserProfileNotFoundException;
import com.vasileva.calorizer.mapper.UserProfileMapper;
import com.vasileva.calorizer.model.user.UserProfile;
import com.vasileva.calorizer.model.user.UserProfileOut;
import com.vasileva.calorizer.model.user.UserProfileUpdateIn;
import com.vasileva.calorizer.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Transactional(readOnly = true)
    public UserProfileOut getUserProfile(String username) {
        UserProfile userProfile = findProfile(username);
        return userProfileMapper.toUserProfileOut(userProfile);
    }

    @Transactional
    public UserProfileOut updateUserProfile(String username, UserProfileUpdateIn request) {
        UserProfile userProfile = findProfile(username);

        System.out.println(username);
        System.out.println(request);

        if (request.getGender() != null) {
            userProfile.setGender(request.getGender());
        }

        if (request.getAge() != null) {
            userProfile.setAge(request.getAge());
        }

        if (request.getHeight() != null) {
            userProfile.setHeight(request.getHeight());
        }

        if (request.getWeight() != null) {
            userProfile.setWeight(request.getWeight());
        }
        userProfileRepository.save(userProfile);
        return userProfileMapper.toUserProfileOut(userProfile);
    }

    private UserProfile findProfile(String username) {
        return userProfileRepository.findByUser_Name(username).orElseThrow(
                () -> new UserProfileNotFoundException(String.format(
                "User profile with name=%s not found", username)));
    }
}
