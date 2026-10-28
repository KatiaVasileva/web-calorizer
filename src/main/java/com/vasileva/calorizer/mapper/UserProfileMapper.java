package com.vasileva.calorizer.mapper;

import com.vasileva.calorizer.model.user.UserProfile;
import com.vasileva.calorizer.model.user.UserProfileOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(target = "name", source = "user.name")
    UserProfileOut toUserProfileOut(UserProfile userProfile);
}
