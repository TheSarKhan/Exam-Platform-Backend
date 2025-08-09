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

    @Override
    public void saveContent(String header, String username, String refreshToken, Long expireIn) {
        log.info("Saving content for username: {} in redis", username);
        String key = header + username;
        redisTemplate.opsForValue().set(key, refreshToken, expireIn, TimeUnit.MILLISECONDS);
    }

    @Override
    public String getContent(String header, String username) {
        log.info("Getting content for username: {} from redis", username);
        String key = header + username;
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteContent(String header, String username) {
        log.info("Deleting content for username: {} from redis", username);
        redisTemplate.delete(header + username);
    }
}
