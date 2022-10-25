package com.codingjoa.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		log.info("============== home ==============");
		
		return "home";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		log.info("============== accessDenied ==============");
		
		return "access-denied";
	}
	
	@GetMapping("/test1")
	public String test1() {
		log.info("============== test1 ==============");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal != null) {
			log.info("principal is NOT NULL");
//			UserDetails userDetails = (UserDetails) principal;
//			String memberId = userDetails.getUsername();
//			String memberPassword = userDetails.getPassword();
//			log.info("memberId = {}, memberPassword = {}", memberId, memberPassword);
		} else {
			log.info("principal is NULL");
		}
		
		return "home";
	}

	@GetMapping("/test2")
	public String test2(Authentication authentication) {
		log.info("============== test2 ==============");
		
		if(authentication != null) {
			log.info("authentication is NOT NULL");
//			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//			String memberId = userDetails.getUsername();
//			String memberPassword = userDetails.getPassword();
//			log.info("memberId = {}, memberPassword = {}", memberId, memberPassword);
		} else {
			log.info("authentication is NULL");
		}
		
		return "home";
	}
	
	@GetMapping("/test3")
	public String test3(@AuthenticationPrincipal UserDetails userDetails) {
		log.info("============== test3 ==============");
		
		if(userDetails != null) {
			log.info("userDetails is NOT NULL");
//			String memberId = userDetails.getUsername();
//			String memberPassword = userDetails.getPassword();
//			log.info("memberId = {}, memberPassword = {}", memberId, memberPassword);
		} else {
			log.info("userDetails is NULL");
		}
		
		return "home";
	}
	
}
