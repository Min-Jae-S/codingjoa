package com.codingjoa.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

import com.codingjoa.dto.EmailDto;
import com.codingjoa.dto.EmailResponseDTO;
import com.codingjoa.dto.JoinDto;
import com.codingjoa.dto.LoginDto;
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
	
	@Autowired
	private MessageSource messageSource;
	
	@Resource(name = "joinValidator")
	private Validator joinValidator;
	
	@Resource(name = "emailValidator")
	private Validator emailValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		String objectName = binder.getObjectName();

		if(objectName.equals("joinDto")) {
			binder.addValidators(joinValidator);
		} else if(objectName.equals("emailDto")) {
			binder.addValidators(emailValidator);
		}
	}
	
	@GetMapping("/member/join")
	public String join(@ModelAttribute JoinDto joinDto) {
		log.info("join, {}", joinDto);
		
		return "member/join"; 
	}
	
	@PostMapping("/member/joinProc")
	public String joinProc(@Valid @ModelAttribute JoinDto joinDto, BindingResult bindingResult) {
		log.info("joinProc, {}", joinDto);

		if(bindingResult.hasErrors()) {
			return "member/join";
		}
		memberService.register(joinDto);
		
		return "member/join-success"; 
	}
	
	@PostMapping("/member/sendAuthEmail")
	@ResponseBody
	public EmailResponseDTO sendAuthEmail(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {
		log.info("sendAuthEmail, {}", emailDto);
		
		EmailResponseDTO emailResponseDTO = new EmailResponseDTO();

		if(bindingResult.hasErrors()) {
			String code = bindingResult.getAllErrors().get(0).getCodes()[0];
			emailResponseDTO.setErrorMessage(messageSource.getMessage(code, null, null));
			emailResponseDTO.setValidated(false);
		} else {
			emailResponseDTO.setValidated(true);
			emailService.sendAuthEmail(emailDto);
		}
		
		return emailResponseDTO;
	}
	
	@GetMapping("/member/login")
	public String login(@ModelAttribute LoginDto loginDto) {
		log.info("login, {}", loginDto);
		
		return "member/login";
	}
	
	
}
