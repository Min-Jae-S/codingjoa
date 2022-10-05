package com.codingjoa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.service.MemberService;
import com.codingjoa.validation.JoinValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/join")
	public String join(@ModelAttribute MemberVO memberVO) {
		log.info("====================== join ======================");
		log.info("member = {}", memberVO);
		
		return "member/join"; 
	}
	
	@PostMapping("/joinProc")
	public String joinProc(@Valid @ModelAttribute MemberVO memberVO, BindingResult result) {
		log.info("====================== joinProc ======================");
		log.info("member = {}", memberVO);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> {
				log.info(e.getCodes()[0]);
			});
			return "member/join";
		}
		
		return "member/join-success"; 
	}
	
	@GetMapping("/login")
	public String login() {
		log.info("====================== login ======================");
		return "member/login";
	}
	
	
	/******************************************************************************/
	@GetMapping("/register")
	public String register() {
		log.info("====================== register ======================");
		return "member/register";
	}
	
	@PostMapping("/registerProc")
	public String registerProc() {
		log.info("====================== registerProc ======================");
		return "";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new JoinValidator());
	}
}
