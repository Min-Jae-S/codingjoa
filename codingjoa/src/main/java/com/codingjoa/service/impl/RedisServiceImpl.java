package com.codingjoa.service.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.codingjoa.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void saveAuthCode(String memberEmail, String authCode) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(memberEmail, authCode, Duration.ofMinutes(5L));
	}

	@Override
	public boolean isAuthCodeValid(String memberEmail, String authCode) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String value = valueOperations.get(memberEmail);
		
		return authCode.equals(value);
	}
}
