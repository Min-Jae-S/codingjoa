package com.codingjoa.service;

import com.codingjoa.dto.EmailRequestDTO;

public interface EmailService {
	
	public void sendAuthEmail(EmailRequestDTO emailReqestDTO);
	
}
