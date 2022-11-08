package com.codingjoa.service;

import com.codingjoa.dto.EmailDto;

public interface EmailService {
	
	public String sendAuthEmail(EmailDto emailDto);
	
}
