package com.codingjoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.service.MemberService;

import lombok.extern.log4j.Log4j;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/getMemberList")
	public String getMemberList(Model model) {
		System.out.println("============= getMemberList =============");

		List<MemberVO> list = memberService.getMemberList();
		for(MemberVO memberVO : list) {
			System.out.println(memberVO);
		}
		
		model.addAttribute("list", list);
		
		return "member/memberList";
	}
}
