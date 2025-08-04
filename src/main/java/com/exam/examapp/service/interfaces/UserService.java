package com.exam.examapp.service.interfaces;

import com.exam.examapp.model.User;

public interface UserService {
    User getByUsername(String username);
}
