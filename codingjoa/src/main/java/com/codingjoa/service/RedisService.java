package com.codingjoa.service;

public interface RedisService {
	
	public void saveAuthCode(String memberEmail, String authCode);
	
	public void loadAuthCode(String memberEmail, String authCode);
}
