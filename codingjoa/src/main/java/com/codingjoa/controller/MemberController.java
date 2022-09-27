package com.codingjoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingjoa.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/join")
	public String join() {
		log.info("====================== join ======================");
		return "member/join"; 
	}
	
	@PostMapping("/joinProc")
	public String joinProc() {
		log.info("====================== joinProc ======================");
		return "";
	}
	
	@GetMapping("/login")
	public String login() {
		log.info("====================== login ======================");
		return "member/login";
	}
}
