package com.codingjoa.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@GetMapping("/member/join")
	public String join(@ModelAttribute MemberVO memberVO) {
		log.info("join, memberVO = {}", memberVO);
		
		return "member/join"; 
	}
	
	@PostMapping("/member/joinProc")
	public String joinProc(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) {
		log.info("joinProc, memberVO = {}", memberVO);

		if(bindingResult.hasErrors()) {
			return "member/join";
		}
		memberService.register(memberVO);
		
		return "member/join-success"; 
	}
	
	@PostMapping("/member/sendAuthEmail")
	@ResponseBody
	public EmailResponseDTO sendAuthEmail(@Valid @RequestBody EmailRequestDTO emailRequestDTO, BindingResult bindingResult) {
		log.info("sendAuthEmail, emailRequestDTO = {}", emailRequestDTO);
		
		EmailResponseDTO emailResponseDTO = new EmailResponseDTO();

		if(bindingResult.hasErrors()) {
			String code = bindingResult.getAllErrors().get(0).getCodes()[0];
			emailResponseDTO.setErrorMessage(messageSource.getMessage(code, null, null));
			emailResponseDTO.setValidated(false);
		} else {
			emailResponseDTO.setValidated(true);
			emailService.sendAuthEmail(emailRequestDTO);
		}
		
		return emailResponseDTO;
	}
	
	@GetMapping("/member/login")
	public String login() {
		log.info("-------- login --------");
		
		return "member/login";
	}
	
	@PostMapping("/member/testRedis")
	@ResponseBody
	public String testRedis(String memberEmail) throws InterruptedException {
		log.info("testRedis, memberEmail = {}", memberEmail);
		
		String authCode = "tj23947";
		
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(memberEmail, authCode);
		log.info("key : {}, value : {}를 Redis에 저장", memberEmail, authCode);
		
		Thread.sleep(3000);
		
		String result = valueOperations.get(memberEmail);
		log.info("Redis에서 조회된 결과, value = {}", result);
		
		return result;
	}
	
	
}
