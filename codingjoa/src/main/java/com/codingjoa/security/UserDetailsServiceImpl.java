package com.codingjoa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.codingjoa.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername, memberId = {}", username);
		
		return null;
	}

}
