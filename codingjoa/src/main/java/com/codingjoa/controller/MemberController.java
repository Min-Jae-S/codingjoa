package com.codingjoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/join")
	public String join(@ModelAttribute MemberVO memberVO) {
		log.info("====================== join ======================");
		log.info("{}", memberVO);
		
		return "member/join"; 
	}
	
	@PostMapping("/joinProc")
	public String joinProc(@ModelAttribute MemberVO memberVO) {
		log.info("====================== joinProc ======================");
		log.info("{}", memberVO);
		
		return "redirect:/member/join";
	}
	
	@GetMapping("/login")
	public String login() {
		log.info("====================== login ======================");
		return "member/login";
	}
}
