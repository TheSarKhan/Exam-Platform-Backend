package com.exam.examapp.mapper;

import com.exam.examapp.dto.response.ProfileResponseDetailed;
import com.exam.examapp.dto.response.ProfileResponseSimple;
import com.exam.examapp.model.User;

public class ProfileMapper {
    public static ProfileResponseSimple userToProfileResponse(User user) {
        return new ProfileResponseSimple(
                user.getId().toString(),
                user.getUsername(),
                user.getProfilePictureUrl(),
                user.getRole());
    }

    public static ProfileResponseDetailed userToProfileResponseDetailed(User user) {
        return new ProfileResponseDetailed(
                user.getId().toString(),
                user.getUsername(),
                user.getProfilePictureUrl(),
                user.getRole(),
                user.getEmail(),
                user.getPhoneNumber());
    }
}
