package com.codingjoa.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
	
	private String memberId;
	private String memberPassword;
}