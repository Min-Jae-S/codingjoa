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

import com.codingjoa.dto.EmailRequestDTO;
import com.codingjoa.dto.EmailResponseDTO;
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
	
	@Autowired
	private MessageSource messageSource;
	
	@Resource(name = "joinValidator")
	private Validator joinValidator;
	
	@Resource(name = "emailValidator")
	private Validator emailValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		String objectName = binder.getObjectName();
		
		if(objectName.equals("memberVO")) {
			binder.setValidator(joinValidator);
		} else if(objectName.equals("emailRequestDTO")) {
			binder.setValidator(emailValidator);
		}
	}
	
	@GetMapping("/member/join")
	public String join(@ModelAttribute MemberVO memberVO) {
		log.info("join, memberVO = {}", memberVO);
		
		return "member/join"; 
	}
	
	@PostMapping("/member/joinProc")
	public String joinProc(@Valid @ModelAttribute MemberVO memberVO, 
						   BindingResult bindingResult) {
		log.info("joinProc, memberVO = {}", memberVO);

		if(bindingResult.hasErrors()) {
			return "member/join";
		}
		memberService.register(memberVO);
		
		return "member/join-success"; 
	}
	
	@PostMapping("/member/authEmail")
	@ResponseBody
	public EmailResponseDTO authEmail(@Valid @RequestBody EmailRequestDTO emailRequestDTO, 
						  			  BindingResult bindingResult) {
		log.info("authEmail, emailRequestDTO = {}", emailRequestDTO);
		
		EmailResponseDTO emailResponseDTO = new EmailResponseDTO();
		if(bindingResult.hasErrors()) {
			emailResponseDTO.setValidated(false);
			String code = bindingResult.getAllErrors().get(0).getCodes()[0];
			emailResponseDTO.setErrorMessage(messageSource.getMessage(code, null, null));
		} else {
			emailResponseDTO.setValidated(true);
		}
		
		return emailResponseDTO;
	}
	
	@GetMapping("/member/login")
	public String login() {
		log.info("-------- login --------");
		
		return "member/login";
	}
	
	
	
}
