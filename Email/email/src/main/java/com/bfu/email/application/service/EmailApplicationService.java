package com.bfu.email.application.service;

import org.springframework.stereotype.Service;

import com.bfu.email.application.repository.EmailRepository;
import com.bfu.email.domain.Email;
import com.bfu.email.domain.dto.EmailRequest;
import com.bfu.email.domain.dto.EmailResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailApplicationService implements EmailService {
	
	private final EmailRepository repository;

	@Override
	public EmailResponse sendEmail(@Valid EmailRequest request) {
		Email email = repository.salva(new Email(request));
		return EmailResponse.builder().emailId(email.getEmailId()).build();
	}
}