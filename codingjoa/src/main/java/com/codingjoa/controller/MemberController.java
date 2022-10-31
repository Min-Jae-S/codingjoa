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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingjoa.dto.EmailDto;
import com.codingjoa.dto.EmailResponseDto;
import com.codingjoa.dto.JoinDto;
import com.codingjoa.dto.LoginDto;
import com.codingjoa.service.EmailService;
import com.codingjoa.service.MemberService;
import com.codingjoa.util.MessageUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member")
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		String objectName = binder.getObjectName();

		if(objectName.equals("joinDto")) {
			binder.addValidators(joinValidator);
		} else if(objectName.equals("emailDto")) {
			binder.addValidators(emailValidator);
		}
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute JoinDto joinDto) {
		log.info("join, {}", joinDto);
		
		return "member/join"; 
	}
	
	@PostMapping("/joinProc")
	public String joinProc(@Valid @ModelAttribute JoinDto joinDto, BindingResult bindingResult) {
		log.info("joinProc, {}", joinDto);

		if(bindingResult.hasErrors()) {
			return "member/join";
		}
		
		memberService.register(joinDto);
		
		return "member/join-success"; 
	}
	
	@PostMapping("/sendAuthEmail")
	@ResponseBody
	public EmailResponseDto sendAuthEmail(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {
		log.info("sendAuthEmail, {}", emailDto);
		
		EmailResponseDto emailResponseDto = new EmailResponseDto();
		
		if(bindingResult.hasErrors()) {
			String code = bindingResult.getAllErrors().get(0).getCodes()[0];
			emailResponseDto.setErrorMessage(MessageUtils.getMessage(code));
			emailResponseDto.setValidated(false);
		} else {
			emailResponseDto.setValidated(true);
			emailService.sendAuthEmail(emailDto);
		}
		
		return emailResponseDto;
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute LoginDto loginDto) {
		log.info("login, {}", loginDto);
		
		return "member/login";
	}

	@GetMapping("/account")
	public String account() {
		return "member/account";
	}

	@GetMapping("/info")
	public String info() {
		return "member/info";
	}
	
	@GetMapping("/beforeChangePassword")
	public String beforeChangePassword() {
		return "member/before-change-password";
	}
	
}
