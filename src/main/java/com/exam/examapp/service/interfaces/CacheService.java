package com.exam.examapp.service.interfaces;

public interface CacheService {
    void saveRefreshToken(String username, String refreshToken);

    String getRefreshToken(String username);

    void deleteRefreshToken(String username) ;
}
