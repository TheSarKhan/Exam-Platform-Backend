package com.exam.examapp.service.impl;

import com.exam.examapp.service.interfaces.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisServiceImpl implements CacheService {
    private final RedisTemplate redisTemplate;

    public RedisServiceImpl(@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRefreshToken(String username, String refreshToken) {
        redisTemplate.opsForValue().set("refresh_token_" + username, refreshToken, 1, TimeUnit.DAYS);
    }

    public String getRefreshToken(String username) {
        return (String) redisTemplate.opsForValue().get("refresh_token_" + username);
    }

    public void deleteRefreshToken(String username) {
        redisTemplate.delete("refresh_token_" + username);
    }
}
