package com.exam.examapp.dto.response;

import com.exam.examapp.model.enums.Role;

public record ProfileResponseDetailed(String id,
                                      String username,
                                      String profilePictureUrl,
                                      Role role,
                                      String email,
                                      String phoneNumber) {
}
