package com.bfu.email.application.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bfu.email.application.service.EmailService;
import com.bfu.email.domain.dto.EmailRequest;
import com.bfu.email.domain.dto.EmailResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailController implements EmailApi {
	private final EmailService service;

	@Override
	public EmailResponse sendingEmail(@Valid EmailRequest request) {
		EmailResponse email = service.sendEmail(request);
		return email;
	}
}