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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingjoa.dto.EmailRequestDTO;
import com.codingjoa.entity.MemberVO;
import com.codingjoa.service.EmailService;
import com.codingjoa.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EmailService emailService;
	
	@Resource(name = "joinValidator")
	private Validator joinValidator;
	
	@Resource(name = "emailValidator")
	private Validator emailValidator;
	
	@InitBinder("memberVO")
	public void initJoinBinder(WebDataBinder binder) {
		binder.addValidators(joinValidator);
	}
	
	@InitBinder("emailRequestDTO")
	public void initEmailBinder(WebDataBinder binder) {
		binder.addValidators(emailValidator);
	}
	
	@GetMapping("/member/join")
	public String join(@ModelAttribute MemberVO memberVO) {
		log.info("join, memberVO = {}", memberVO);
		
		return "member/join"; 
	}
	
	@PostMapping("/member/joinProc")
	public String joinProc(@Valid @ModelAttribute MemberVO memberVO, BindingResult result) {
		log.info("joinProc, memberVO = {}", memberVO);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> 
				log.info(e.getCodes()[0])
			);
			return "member/join";
		}
		memberService.register(memberVO);
		
		return "member/join-success"; 
	}
	
	@PostMapping("/member/authEmail")
	@ResponseBody
	public String authEmail(@Valid @RequestBody EmailRequestDTO emailRequestDTO, 
							BindingResult result) {
		log.info("authEmail, memberEmail = {}", emailRequestDTO.getMemberEmail());
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(objectError -> {
				log.info("code : " + objectError.getCodes()[0]);
				log.info("defaultMessage : " + objectError.getDefaultMessage());
				log.info("objectName : " + objectError.getObjectName());
			});
			
			return "YES error";
		} else {
			return "NO error";
		}
	}
	
	@GetMapping("/member/login")
	public String login() {
		log.info("-------- login --------");
		
		return "member/login";
	}
	
	@GetMapping("/member/sendEmail")
	public String sendEmail() {
		log.info("-------- sendEmail --------");
		
		emailService.sendEmail();
		
		return "redirect:/";
	}

}
