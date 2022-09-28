package com.codingjoa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.service.MemberService;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Log4j
@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/join")
	public String join(@ModelAttribute MemberVO memberVO) {
		log.info("====================== join ======================");
		log.info(memberVO);
		
		return "member/join"; 
	}
	
	@PostMapping("/joinProc")
	public String joinProc(@Valid @ModelAttribute MemberVO memberVO, BindingResult result) {
		log.info("====================== joinProc ======================");
		log.info(memberVO);
		
		if(result.hasErrors()) {
			log.info("-----------------------------------");
			result.getAllErrors().forEach(objectError -> {
				log.info(objectError.getCodes()[0]);
			});
			log.info("-----------------------------------");
			return "member/join";
		}
		
		return "member/join-success"; 
	}
	
	@GetMapping("/login")
	public String login() {
		log.info("====================== login ======================");
		return "member/login";
	}
}
