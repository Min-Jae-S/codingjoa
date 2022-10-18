package com.codingjoa.service;

public interface RedisService {
	
	public void saveAuthCode(String memberEmail, String authCode);
	
	public boolean isAuthCodeValid(String memberEmail, String authCode);
}
