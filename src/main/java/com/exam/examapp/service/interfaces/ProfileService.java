package com.exam.examapp.service.interfaces;

import com.exam.examapp.dto.response.ExamResponseSimple;
import com.exam.examapp.dto.response.ProfileResponseDetailed;
import com.exam.examapp.dto.response.ProfileResponseSimple;

import java.util.List;

public interface ProfileService {
    ProfileResponseSimple getCurrentProfile();

    List<ExamResponseSimple> getMyExams();

    ProfileResponseDetailed getProfileSettings(String username);
}
