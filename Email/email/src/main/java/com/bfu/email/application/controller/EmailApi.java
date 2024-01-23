package com.bfu.email.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bfu.email.domain.dto.EmailRequest;
import com.bfu.email.domain.dto.EmailResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public interface EmailApi {
	
	@PostMapping("/send-email")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmailResponse sendingEmail(@RequestBody @Valid EmailRequest request);
}
