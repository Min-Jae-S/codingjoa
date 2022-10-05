package com.codingjoa.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.service.MemberService;
import com.codingjoa.validation.JoinValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Resource(name = "joinValidator")
	private Validator joinValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(joinValidator);
	}
	
	@GetMapping("/member/join")
	public String join(@ModelAttribute MemberVO memberVO) {
		log.info("====================== join ======================");
		log.info("member = {}", memberVO);
		
		return "member/join"; 
	}
	
	@PostMapping("/member/joinProc")
	public String joinProc(@Valid @ModelAttribute MemberVO memberVO, BindingResult result) {
		log.info("====================== joinProc ======================");
		log.info("member = {}", memberVO);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> {
				log.info(e.getCodes()[0]);
			});
			return "member/join";
		}
		
		memberService.register(memberVO);
		
		return "member/join-success"; 
	}
	
	@GetMapping("/member/login")
	public String login() {
		log.info("====================== login ======================");
		return "member/login";
	}
	
	
}
