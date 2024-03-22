package com.example.demo.service;

import com.example.demo.data.CacheableEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisService {

    private final RedisTemplate<String, CacheableEntity> redisTemplate;

    public RedisService(RedisTemplate<String, CacheableEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, CacheableEntity data) {
        redisTemplate.opsForValue().set(key, data);
    }

    public CacheableEntity getData(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElseThrow();
    }

}
